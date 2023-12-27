package com.example.redis.config;

import com.example.redis.service.JwtService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {

    private final JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String accessToken = request.getHeader("Authorization");
        try {
            Long userId = jwtService.getSubject(accessToken);
            request.setAttribute("userId", userId);
        } catch (JwtException e) {
            throw new IllegalArgumentException("미인증 사용자 요청입니다.");
        }

        return true;
    }

}
