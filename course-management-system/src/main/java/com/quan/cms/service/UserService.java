package com.quan.cms.service;

import com.quan.cms.dto.request.*;
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
    void changePassword(

            Long userId,

            ChangePasswordRequest request,

            String username
    );
}