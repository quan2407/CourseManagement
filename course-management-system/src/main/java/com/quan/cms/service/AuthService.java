package com.quan.cms.service;

import com.quan.cms.dto.request.LoginRequest;
import com.quan.cms.dto.response.LoginResponse;
import com.quan.cms.dto.response.MeResponse;
import org.springframework.security.core.Authentication;

public interface AuthService {

    LoginResponse login(LoginRequest request);

    MeResponse getCurrentUser(Authentication authentication);
}