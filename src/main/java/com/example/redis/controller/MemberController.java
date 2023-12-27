package com.example.redis.controller;

import com.example.redis.dto.MemberSaveRequest;
import com.example.redis.dto.TokenResponse;
import com.example.redis.repository.RefreshTokenRepository;
import com.example.redis.service.JwtService;
import com.example.redis.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;

    @PostMapping("member/save")
    public TokenResponse saveMember(@RequestBody MemberSaveRequest request) {
        Long memberId = memberService.save(request);
        String accessToken = jwtService.generateAccessToken(memberId);
        String refreshToken = jwtService.generateRefreshToken(memberId);
        refreshTokenRepository.saveRefreshToken(memberId, refreshToken);
        return new TokenResponse(accessToken, refreshToken);
    }

}
