package com.example.edoc.dto.request;

import com.example.edoc.entity.Hoso;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTkRequest {
    private String taikhoan;
    private String matkhau;
    private HosoDto hoso;

}
