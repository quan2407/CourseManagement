package com.quan.cms.controller;

import com.quan.cms.dto.request.CreateUserRequest;
import com.quan.cms.dto.response.ApiResponse;
import com.quan.cms.dto.response.UserResponse;
import com.quan.cms.enums.Role;
import com.quan.cms.service.UserService;
import com.quan.cms.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<UserResponse>> getAllUsers(

            @RequestParam(required = false)
            Role role,

            @RequestParam(required = false)
            Boolean isActive
    ) {

        return ResponseUtil.success(
                "Users retrieved successfully",
                userService.getAllUsers(role, isActive)
        );
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<UserResponse> getUserById(
            @PathVariable Long userId
    ) {

        return ResponseUtil.success(
                "User retrieved successfully",
                userService.getUserById(userId)
        );
    }
}