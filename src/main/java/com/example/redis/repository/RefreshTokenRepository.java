package com.example.redis.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import static java.util.concurrent.TimeUnit.DAYS;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RefreshTokenRepository {

    private final RedisTemplate redisTemplate;

    public void saveRefreshToken(String accessToken, String refreshToken) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        //key = accessToekn value = refreshToken
        valueOperations.set(accessToken, refreshToken);
        log.info("=======레디스에 저장========");
        //유효기간 만료 1년
        valueOperations.getAndExpire(accessToken, 365, DAYS);
    }

    public String findByAccessToken(String accessToken) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String refreshToken = valueOperations.get(accessToken);
        log.info("refreshToken = {} ", refreshToken);
        if (refreshToken.equals("") || refreshToken == null) {
            throw new IllegalArgumentException("존재하지 않는 토큰입니다.");
        }
        return refreshToken;
    }

}
