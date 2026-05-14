package com.quan.cms.service.impl;

import com.quan.cms.service.TokenBlacklistService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TokenBlacklistServiceImpl
        implements TokenBlacklistService {

    private final Set<String> blacklistedTokens =
            new HashSet<>();

    @Override
    public void blacklistToken(String token) {

        blacklistedTokens.add(token);
    }

    @Override
    public boolean isBlacklisted(String token) {

        return blacklistedTokens.contains(token);
    }
}