package com.quan.cms.controller;

import com.quan.cms.dto.request.CreateUserRequest;
import com.quan.cms.dto.response.ApiResponse;
import com.quan.cms.dto.response.UserResponse;
import com.quan.cms.service.UserService;
import com.quan.cms.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<UserResponse> createUser(
            @Valid @RequestBody CreateUserRequest request
    ) {

        return ResponseUtil.success(
                "User created successfully",
                userService.createUser(request)
        );
    }
}