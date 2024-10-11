package com.ssafy.ios.lineup.backend.common.constant.error_code;

import com.ssafy.ios.lineup.backend.common.constant.error_code.base.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ApplicationErrorCode implements ErrorCode {
    NO_APPLICANT_INFO(HttpStatus.NOT_FOUND, "해당 지원 내역을 찾을 수 없습니다");

    private final HttpStatus httpStatus;
    private final String message;
}
