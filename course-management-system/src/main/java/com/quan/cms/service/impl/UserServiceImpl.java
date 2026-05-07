package com.quan.cms.service.impl;

import com.quan.cms.dto.request.CreateUserRequest;
import com.quan.cms.dto.response.UserResponse;
import com.quan.cms.entity.User;
import com.quan.cms.exception.BadRequestException;
import com.quan.cms.mapper.UserMapper;
import com.quan.cms.repository.UserRepository;
import com.quan.cms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}