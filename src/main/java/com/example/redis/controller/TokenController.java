package com.example.redis.controller;

import com.example.redis.dto.RefreshTokenResponse;
import com.example.redis.repository.RefreshTokenRepository;
import com.example.redis.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;

    @PostMapping("/reissue")
    public RefreshTokenResponse reissueRefreshToken(@RequestHeader String accessToken) {
        String refreshToken = refreshTokenRepository.findByAccessToken(accessToken);
        //검증, 재발급
        String newRefreshToken = jwtService.reissueAccessToken(refreshToken);
        return new RefreshTokenResponse(newRefreshToken);
    }

}
