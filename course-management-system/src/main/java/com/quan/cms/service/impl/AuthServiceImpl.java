package com.quan.cms.service.impl;

import com.quan.cms.dto.request.LoginRequest;
import com.quan.cms.dto.response.LoginResponse;
import com.quan.cms.dto.response.MeResponse;
import com.quan.cms.security.JwtUtil;
import com.quan.cms.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @Override
    public LoginResponse login(LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                );

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        String token = jwtUtil.generateToken(userDetails);

        return LoginResponse.builder()
                .token(token)
                .username(userDetails.getUsername())
                .role(
                        userDetails.getAuthorities()
                                .iterator()
                                .next()
                                .getAuthority()
                )
                .build();
    }
    @Override
    public MeResponse getCurrentUser(
            Authentication authentication
    ) {

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        return MeResponse.builder()
                .username(userDetails.getUsername())
                .role(
                        userDetails.getAuthorities()
                                .iterator()
                                .next()
                                .getAuthority()
                                .replace("ROLE_", "")
                )
                .build();
    }
}