package com.ssafy.ios.lineup.backend.common.constant.error_code;

import com.ssafy.ios.lineup.backend.common.constant.error_code.base.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum RecruitErrorCode implements ErrorCode {
    NO_RECRUIT(HttpStatus.NOT_FOUND, "해당 공고가 존재하지 않습니다"),
    ALREADY_STARTED_RECRUIT(HttpStatus.BAD_REQUEST, "해당 공고는 이미 시작되었습니다");

    private final HttpStatus httpStatus;
    private final String message;

}
