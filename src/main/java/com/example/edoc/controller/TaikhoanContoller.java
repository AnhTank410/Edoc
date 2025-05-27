package com.example.edoc.controller;

import com.example.edoc.dto.request.CreateTkRequest;
import com.example.edoc.dto.request.UpdateTkRequest;
import com.example.edoc.dto.response.ApiResponse;
import com.example.edoc.dto.response.TaikhoanResponse;
import com.example.edoc.entity.Taikhoan;
import com.example.edoc.service.TaikhoanService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taikhoan")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
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
    @GetMapping("/myInfo")
    ApiResponse<TaikhoanResponse> getMyInfo(){
        return ApiResponse.<TaikhoanResponse>builder()
                .data(taikhoanService.getMyInfo())
                .build();
    }
    @GetMapping("/{Id}")
    ApiResponse<TaikhoanResponse> getTaiKhoan(@PathVariable("Id") Long id){

        return ApiResponse.<TaikhoanResponse>builder()
                .data(taikhoanService.getTaiKhoan(id))
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<TaikhoanResponse> updateTaiKhoan(@PathVariable Long id,@RequestBody UpdateTkRequest tkRequest){
        return ApiResponse.<TaikhoanResponse>builder()
                .data(taikhoanService.updateTaikhoan(id,tkRequest)).build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> deleteTaiKhoan(@PathVariable Long id){
        taikhoanService.deleteTaikhoan(id);
        return ApiResponse.<String>builder().data("success").build();
    }

}
