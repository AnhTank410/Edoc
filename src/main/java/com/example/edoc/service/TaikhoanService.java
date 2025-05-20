package com.example.edoc.service;

import com.example.edoc.constants.PredefinedRole;
import com.example.edoc.dto.request.CreateTkRequest;
import com.example.edoc.dto.request.HosoDto;
import com.example.edoc.dto.request.UpdateTkRequest;
import com.example.edoc.dto.response.TaikhoanResponse;
import com.example.edoc.entity.Hoso;
import com.example.edoc.entity.Taikhoan;
import com.example.edoc.entity.Vaitro;
import com.example.edoc.exception.AppException;
import com.example.edoc.exception.ErrorCode;
import com.example.edoc.repository.TaikhoanReponsitory;
import com.example.edoc.repository.VaitroReponsitory;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class TaikhoanService {
    @Autowired
    private TaikhoanReponsitory taikhoanReponsitory;
    @Autowired
    private VaitroReponsitory vaitroReponsitory;
    @Autowired
    private PasswordEncoder passwordEncoder;



    public TaikhoanResponse createTaikhoan(CreateTkRequest request){

        if (taikhoanReponsitory.existsByTaikhoan(request.getTaikhoan()))
            throw new AppException(ErrorCode.USER_EXISTED);

        Taikhoan taikhoan=new Taikhoan();
        taikhoan.setTaikhoan(request.getTaikhoan());

        taikhoan.setMatkhau(passwordEncoder.encode(request.getMatkhau()));
        Hoso hoso = convertHosoDtoToHoso(request.getHoso());
        // Gán chiều ngược lại
        hoso.setTaikhoan(taikhoan);
        taikhoan.setHoso(hoso);
        HashSet<Vaitro> vaitros = new HashSet<>();
        Vaitro userRole = vaitroReponsitory.findByTen(PredefinedRole.ROLE_USER)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        vaitros.add(userRole);
        taikhoan.setVaitro(vaitros);

        taikhoanReponsitory.save(taikhoan);

        return TaikhoanResponse.builder()
                .taikhoan(taikhoan.getTaikhoan())
                .hoso(request.getHoso())
                .build();

    }

    public TaikhoanResponse updateTaikhoan(int id, UpdateTkRequest request){
        Taikhoan taikhoan=taikhoanReponsitory.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND));
        taikhoan.setMatkhau(passwordEncoder.encode(request.getMakhau()));
        taikhoan.setHoso(convertHosoDtoToHoso(request.getHosoDto()));
        taikhoanReponsitory.save(taikhoan);
        return TaikhoanResponse.builder()
                .taikhoan(taikhoan.getTaikhoan())
                .build();
    }
    public void deleteTaikhoan(int id){
        taikhoanReponsitory.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<TaikhoanResponse> getTaikhoan() {
        return taikhoanReponsitory.findAll().stream()
                .map(this::convertTaikhoanToTaikhoanResponse)
                .toList();
    }

    public Hoso convertHosoDtoToHoso(HosoDto hosoDto){
        Hoso hoso=new Hoso();
        hoso.setHoten(hosoDto.getHoten());
        hoso.setDienthoai(hosoDto.getDienthoai());
        hoso.setEmail(hosoDto.getEmail());
        hoso.setNgaysinh(hosoDto.getNgaysinh());
        return hoso;
    }

    public TaikhoanResponse convertTaikhoanToTaikhoanResponse(Taikhoan taikhoan){
        TaikhoanResponse taikhoanResponse=new TaikhoanResponse();
        taikhoanResponse.setTaikhoan(taikhoan.getTaikhoan());
        HosoDto hosoDto=new HosoDto();
        hosoDto.setHoten(taikhoan.getHoso().getHoten());
        hosoDto.setDienthoai(taikhoan.getHoso().getDienthoai());
        hosoDto.setEmail(taikhoan.getHoso().getEmail());
        hosoDto.setNgaysinh(taikhoan.getHoso().getNgaysinh());
        taikhoanResponse.setHoso(hosoDto);
        return taikhoanResponse;
    }

}

