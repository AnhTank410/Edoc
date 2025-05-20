package com.example.edoc.controller;

import com.example.edoc.dto.request.CreateTkRequest;
import com.example.edoc.dto.response.ApiResponse;
import com.example.edoc.dto.response.TaikhoanResponse;
import com.example.edoc.service.TaikhoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taikhoan")
@RequiredArgsConstructor
public class TaikhoanContoller {

    @Autowired
    TaikhoanService taikhoanService;
    @PostMapping
    ApiResponse<TaikhoanResponse> createTaikhoan(@RequestBody CreateTkRequest tkRequest){
        return ApiResponse.<TaikhoanResponse>builder()
                .data(taikhoanService.createTaikhoan(tkRequest))
                .build();
    }

    @GetMapping
    ApiResponse<List<TaikhoanResponse>> getAllTaikhoan(){
        return ApiResponse.<List<TaikhoanResponse>>builder()
                .data(taikhoanService.getTaikhoan())
                .build();
    }
}
