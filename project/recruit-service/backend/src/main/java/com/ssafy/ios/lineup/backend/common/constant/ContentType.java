package com.ssafy.ios.lineup.backend.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ContentType {
    TEXT("텍스트"),
    IMAGE("이미지"),
    SYSTEM("시스템");

    private final String value;
}
