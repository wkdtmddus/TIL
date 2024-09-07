package com.sparta.toogo.domain.kakao.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserInfoDto {

    private Long id;
    private String email;
    private String nickname;

    public KakaoUserInfoDto(Long id, String email, String nickname) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }

    public KakaoUserInfoDto(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}