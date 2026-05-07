package com.quan.cms.controller;

import com.quan.cms.dto.request.LoginRequest;
import com.quan.cms.dto.response.*;
import com.quan.cms.service.AuthService;
import com.quan.cms.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {

        return ResponseUtil.success(
                "Login successful",
                authService.login(request)
        );
    }
    @GetMapping("/me")
    public ApiResponse<MeResponse> me(
            Authentication authentication
    ) {

        return ResponseUtil.success(
                "User profile retrieved successfully",
                authService.getCurrentUser(authentication)
        );
    }
}