package com.ssafy.ios.lineup.backend.common.constant.error_code;

import com.ssafy.ios.lineup.backend.common.constant.error_code.base.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ContractRequestErrorCode implements ErrorCode {
    NO_CONTRACT_REQUEST(HttpStatus.NOT_FOUND, "해당 계약 요청 정보가 없습니다"),
    ALREADY_EXIST_CONTRACT_REQUEST(HttpStatus.BAD_REQUEST, "해당 공고에 대해 이미 계약 요청이 존재합니다"),
    ALREADY_DENIED_OR_CANCELED_CONTRACT_REQUEST(HttpStatus.BAD_REQUEST, "이미 거절되거나 삭제된 계약요청입니다");

    private final HttpStatus httpStatus;
    private final String message;
}
