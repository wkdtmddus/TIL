package com.ssafy.ios.lineup.backend.common.constant.exception_message;

import lombok.Getter;

@Getter
public enum ApplicationExceptionMessage {
    NO_APPLICATION("해당 지원 내역을 찾을 수 없습니다");

    private final String message;

    ApplicationExceptionMessage(String message) {
        this.message = message;
    }
}
