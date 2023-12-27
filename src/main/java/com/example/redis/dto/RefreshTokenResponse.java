package com.example.redis.dto;

import lombok.Getter;

@Getter
public class RefreshTokenResponse {

    private final String refreshToken;

    public RefreshTokenResponse(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
