package com.example.edoc.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HosoDto {
    private String hoten;
    private String dienthoai;
    private String email;
    private String ngaysinh;
}
