package com.example.redis.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RefreshTokenRepository {

    private final RedisTemplate redisTemplate;

    public void saveRefreshToken(Long memberId, String refreshToken) {
        ValueOperations<Long, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(memberId, refreshToken);
        log.info("=======레디스에 저장========");
    }

    public String findByMemberId(Long memberId) {
        ValueOperations<Long, String> valueOperations = redisTemplate.opsForValue();
        String refreshToken = valueOperations.get(memberId);
        log.info("refreshToken = {} ", refreshToken);
        if (refreshToken.equals("") || refreshToken == null) {
            throw new IllegalArgumentException("존재하지 않는 토큰입니다.");
        }
        return refreshToken;
    }

}
