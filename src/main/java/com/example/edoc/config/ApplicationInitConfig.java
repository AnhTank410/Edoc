package com.example.edoc.config;

import com.example.edoc.constants.PredefinedRole;
import com.example.edoc.entity.Vaitro;
import com.example.edoc.repository.QuyenReponsitory;
import com.example.edoc.repository.TaikhoanReponsitory;
import com.example.edoc.repository.VaitroReponsitory;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
public class ApplicationInitConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @NonFinal
    private static final String ADMIN_PASSWORD="admin";
    @NonFinal
    private static final String ADMIN_USERNAME="admin";

    @Bean
    ApplicationRunner applicationRunner(TaikhoanReponsitory taikhoanReponsitory, VaitroReponsitory vaitroReponsitory){
        return args -> {
            if(taikhoanReponsitory.findByTaikhoan(ADMIN_USERNAME).isEmpty()){
                vaitroReponsitory.save(Vaitro.builder()
                                .ten(PredefinedRole.ROLE_USER).build());

                Vaitro adminvt=vaitroReponsitory.save(Vaitro.builder()
                        .ten(PredefinedRole.ROLE_ADMIN).build());

                var vt= new HashSet<Vaitro>();
                vt.add(adminvt);
                taikhoanReponsitory.save(com.example.edoc.entity.Taikhoan.builder()
                        .taikhoan(ADMIN_USERNAME)
                        .matkhau(passwordEncoder.encode(ADMIN_PASSWORD))
                        .vaitro(vt)
                        .build());
            }
        };
    }

}
