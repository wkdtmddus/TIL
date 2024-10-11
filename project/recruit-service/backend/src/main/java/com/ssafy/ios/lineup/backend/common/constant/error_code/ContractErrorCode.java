package com.ssafy.ios.lineup.backend.common.constant.error_code;

import com.ssafy.ios.lineup.backend.common.constant.error_code.base.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ContractErrorCode implements ErrorCode {
    NO_CONTRACT(HttpStatus.NOT_FOUND, "해당 계약 정보가 없습니다"),
    ONE_HOUR_EXCEEDED_CONTRACT_CREATION_TIME(HttpStatus.BAD_REQUEST, "계약 시간 이후 1시간을 초과했습니다");

    private final HttpStatus httpStatus;
    private final String message;
}
