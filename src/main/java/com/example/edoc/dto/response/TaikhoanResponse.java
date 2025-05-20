package com.example.edoc.dto.response;

import com.example.edoc.dto.request.HosoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaikhoanResponse {
    private String taikhoan;
    private HosoDto hoso;
}
