package com.quan.cms.controller;

import com.quan.cms.dto.request.CreateUserRequest;
import com.quan.cms.dto.request.UpdateProfileRequest;
import com.quan.cms.dto.request.UpdateUserRoleRequest;
import com.quan.cms.dto.request.UpdateUserStatusRequest;
import com.quan.cms.dto.response.ApiResponse;
import com.quan.cms.dto.response.UserResponse;
import com.quan.cms.enums.Role;
import com.quan.cms.service.UserService;
import com.quan.cms.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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

    @PutMapping("/{userId}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<UserResponse> updateUserRole(

            @PathVariable Long userId,

            @Valid
            @RequestBody
            UpdateUserRoleRequest request
    ) {

        return ResponseUtil.success(
                "User role updated successfully",
                userService.updateUserRole(userId, request)
        );
    }

    @PutMapping("/{userId}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<UserResponse> updateUserStatus(

            @PathVariable Long userId,

            @Valid
            @RequestBody
            UpdateUserStatusRequest request
    ) {

        return ResponseUtil.success(
                "User status updated successfully",
                userService.updateUserStatus(userId, request)
        );
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Object> deleteUser(
            @PathVariable Long userId
    ) {

        userService.deleteUser(userId);

        return ResponseUtil.success(
                "User deleted successfully",
                null
        );
    }
    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> updateProfile(

            @PathVariable Long userId,

            @Valid
            @RequestBody
            UpdateProfileRequest request,

            Authentication authentication
    ) {

        return ResponseUtil.success(
                "Profile updated successfully",
                userService.updateProfile(
                        userId,
                        request,
                        authentication.getName()
                )
        );
    }
}