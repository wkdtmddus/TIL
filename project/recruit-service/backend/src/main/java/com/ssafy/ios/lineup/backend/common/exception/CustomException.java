package com.ssafy.ios.lineup.backend.common.exception;

import com.ssafy.ios.lineup.backend.common.constant.error_code.base.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;

    /* 서비스(Service, Facade) 로직에서 예외 처리 시 사용할 생성자 */
    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}