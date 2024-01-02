package com.example.redis.controller;

import com.example.redis.dto.RefreshTokenResponse;
import com.example.redis.repository.TokenRepository;
import com.example.redis.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final JwtService jwtService;
    private final TokenRepository tokenRepository;

    @PostMapping("/reissue")
    public RefreshTokenResponse reissueRefreshToken(@RequestHeader String accessToken) {
        String refreshToken = tokenRepository.findByAccessToken(accessToken);
        //검증, 재발급
        String newAccessToken = jwtService.reissueAccessToken(refreshToken);
        tokenRepository.saveRefreshToken(newAccessToken, refreshToken);
        return new RefreshTokenResponse(newAccessToken);
    }

}
