package com.quan.cms.service.impl;

import com.quan.cms.dto.request.CreateUserRequest;
import com.quan.cms.dto.request.UpdateUserRoleRequest;
import com.quan.cms.dto.request.UpdateUserStatusRequest;
import com.quan.cms.dto.response.UserResponse;
import com.quan.cms.entity.User;
import com.quan.cms.enums.Role;
import com.quan.cms.exception.BadRequestException;
import com.quan.cms.exception.ResourceNotFoundException;
import com.quan.cms.mapper.UserMapper;
import com.quan.cms.repository.UserRepository;
import com.quan.cms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(
            CreateUserRequest request
    ) {

        if (userRepository.existsByUsername(
                request.getUsername()
        )) {

            throw new BadRequestException(
                    "Username already exists"
            );
        }

        if (userRepository.existsByEmail(
                request.getEmail()
        )) {

            throw new BadRequestException(
                    "Email already exists"
            );
        }

        User user = userMapper.toEntity(request);

        user.setPasswordHash(
                passwordEncoder.encode(request.getPassword())
        );

        user.setIsActive(true);

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    @Override
    public List<UserResponse> getAllUsers(
            Role role,
            Boolean isActive
    ) {

        List<User> users;

        if (role != null && isActive != null) {

            users = userRepository.findByRoleAndIsActive(
                    role,
                    isActive
            );

        } else if (role != null) {

            users = userRepository.findByRole(role);

        } else if (isActive != null) {

            users = userRepository.findByIsActive(isActive);

        } else {

            users = userRepository.findAll();
        }

        return users.stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse getUserById(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new BadRequestException(
                                "User not found"
                        )
                );

        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse updateUserRole(
            Long userId,
            UpdateUserRoleRequest request
    ) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        )
                );

        if (user.getRole() == Role.ADMIN) {

            throw new BadRequestException(
                    "Cannot update another admin role"
            );
        }

        user.setRole(request.getRole());

        User updatedUser = userRepository.save(user);

        return userMapper.toResponse(updatedUser);
    }

    @Override
    public UserResponse updateUserStatus(
            Long userId,
            UpdateUserStatusRequest request
    ) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        )
                );

        user.setIsActive(request.getIsActive());

        User updatedUser = userRepository.save(user);

        return userMapper.toResponse(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        )
                );

        if (user.getRole() == Role.ADMIN) {

            throw new BadRequestException(
                    "Cannot delete admin account"
            );
        }

        userRepository.delete(user);
    }
}