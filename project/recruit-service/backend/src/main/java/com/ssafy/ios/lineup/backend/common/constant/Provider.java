package com.ssafy.ios.lineup.backend.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Provider {
    NAVER("네이버"),
    KAKAO("카카오"),
    GOOGLE("구글");

    private final String value;
}
