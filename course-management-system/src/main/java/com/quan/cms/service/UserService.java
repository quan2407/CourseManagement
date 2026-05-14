package com.quan.cms.service;

import com.quan.cms.dto.request.CreateUserRequest;
import com.quan.cms.dto.request.UpdateProfileRequest;
import com.quan.cms.dto.request.UpdateUserRoleRequest;
import com.quan.cms.dto.request.UpdateUserStatusRequest;
import com.quan.cms.dto.response.UserResponse;
import com.quan.cms.enums.Role;

import java.util.List;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    List<UserResponse> getAllUsers(
            Role role,
            Boolean isActive
    );

    UserResponse getUserById(Long userId);

    UserResponse updateUserRole(
            Long userId,
            UpdateUserRoleRequest request
    );

    UserResponse updateUserStatus(
            Long userId,
            UpdateUserStatusRequest request
    );

    void deleteUser(Long userId);

    UserResponse updateProfile(

            Long userId,

            UpdateProfileRequest request,

            String username
    );
}