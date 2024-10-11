package com.ssafy.ios.lineup.backend.common.constant.error_code;

import com.ssafy.ios.lineup.backend.common.constant.error_code.base.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCode {
    NO_LOGIN_USER(HttpStatus.BAD_REQUEST, "로그인한 유저가 없습니다"),
    NO_USER(HttpStatus.BAD_REQUEST, "유저가 없습니다"),
    NO_NICKNAME(HttpStatus.BAD_REQUEST, "해당하는 닉네임이 없습니다"),
    WRONG_LOGIN_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 로그인 시도입니다."),
    NO_EMAIL(HttpStatus.BAD_REQUEST, "찾을 수 없는 이메일입니다."),
    NO_RECRUIT_APPLICANT(HttpStatus.BAD_REQUEST, "해당하는 공고의 지원자가 아닙니다"),
    NO_RECRUIT_WRITER(HttpStatus.BAD_REQUEST, "해당하는 공고의 공고자가 아닙니다"),
    NO_REQUIRED_USER_IN_CONTRACT(HttpStatus.BAD_REQUEST, "해당 계약에 해당하는 유저가 아닙니다"),
    NO_REQUIRED_USER_IN_CONTRACT_REQUEST(HttpStatus.BAD_REQUEST, "해당 계약 요청에 해당하는 유저가 아닙니다");

    private final HttpStatus httpStatus;
    private final String message;

}
