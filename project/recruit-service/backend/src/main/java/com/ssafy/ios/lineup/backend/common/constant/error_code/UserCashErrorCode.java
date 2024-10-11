package com.ssafy.ios.lineup.backend.common.constant.error_code;

import com.ssafy.ios.lineup.backend.common.constant.error_code.base.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserCashErrorCode implements ErrorCode {
    NO_USER_CASH_INFO(HttpStatus.BAD_REQUEST, "유저의 잔액 정보가 없습니다"),
    LACK_OF_CASH(HttpStatus.BAD_REQUEST, "잔액이 부족합니다");

    private final HttpStatus httpStatus;
    private final String message;

}
