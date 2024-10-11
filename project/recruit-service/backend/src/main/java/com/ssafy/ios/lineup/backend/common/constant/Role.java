package com.ssafy.ios.lineup.backend.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ROLE_ADMIN("관리자"),
    ROLE_USER("사용자");

    private final String value;
}
