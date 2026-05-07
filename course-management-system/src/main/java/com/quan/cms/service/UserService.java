package com.quan.cms.service;

import com.quan.cms.dto.request.CreateUserRequest;
import com.quan.cms.dto.response.UserResponse;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);
}