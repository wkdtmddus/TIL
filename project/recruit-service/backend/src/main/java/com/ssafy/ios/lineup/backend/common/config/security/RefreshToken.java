package com.ssafy.ios.lineup.backend.common.config.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@Getter
@RedisHash(value = "refreshToken", timeToLive = 1444000)
public class RefreshToken {

    @Id
    private String refreshToken;
    private Long userId;

    @Value("${refresh-token.milli-second}")
    private long timeToLive;

    public RefreshToken(String refreshToken, Long userId) {
        this.refreshToken = refreshToken;
        this.userId = userId;
    }
}
