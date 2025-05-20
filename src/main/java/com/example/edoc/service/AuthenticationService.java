package com.example.edoc.service;

import com.example.edoc.dto.request.AuthenticationRequest;
import com.example.edoc.dto.response.AuthenticationReponse;
import com.example.edoc.entity.Taikhoan;
import com.example.edoc.exception.AppException;
import com.example.edoc.exception.ErrorCode;
import com.example.edoc.repository.TaikhoanReponsitory;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private final TaikhoanReponsitory taikhoanReponsitory;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SINGER_KEY;

    public AuthenticationReponse authenticate(AuthenticationRequest request){
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder(10);
        var taikhoan=taikhoanReponsitory.findByTaikhoan(request.getTaikhoan())
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));
        if (!passwordEncoder.matches(request.getMatkhau(),taikhoan.getMatkhau())){
            throw new AppException(ErrorCode.INVALID_KEY);
        }
        return AuthenticationReponse.builder()
                .token(generateToken(taikhoan))
                .authenticated(true)
                .build();
    }
    private String generateToken(Taikhoan taikhoan){
        JWSHeader header=new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet=new JWTClaimsSet.Builder()
                .subject(taikhoan.getTaikhoan())
                .issuer("edoc")
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis()+1000*60*60))
                .claim("scope",builderScope(taikhoan))
                .build();
        Payload payload=new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject=new JWSObject(header,payload);
        try {
            jwsObject.sign(new MACSigner(SINGER_KEY.getBytes()));
            return jwsObject.serialize();
        }catch (JOSEException e){
            throw new RuntimeException(e);
        }
    }

    private String builderScope(Taikhoan taikhoan){
        StringJoiner stringJoiner=new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(taikhoan.getVaitro())){
            taikhoan.getVaitro().forEach(vaitro -> {
                stringJoiner.add("ROLE_"+vaitro.getTen());
                if (!CollectionUtils.isEmpty(vaitro.getQuyen())){
                    vaitro.getQuyen().forEach(quyen -> stringJoiner.add(quyen.getTen()));
                }
            });
        }
        return stringJoiner.toString();
    }
}
