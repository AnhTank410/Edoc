package com.example.edoc.controller;

import com.example.edoc.dto.request.AuthenticationRequest;
import com.example.edoc.dto.response.ApiResponse;
import com.example.edoc.dto.response.AuthenticationReponse;
import com.example.edoc.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationReponse> authenticate(@RequestBody AuthenticationRequest request){
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationReponse>builder()
                .data(result)
                .build();
    }
}
