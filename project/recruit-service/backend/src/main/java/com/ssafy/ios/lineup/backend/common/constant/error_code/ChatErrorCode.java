package com.ssafy.ios.lineup.backend.common.constant.error_code;

import com.ssafy.ios.lineup.backend.common.constant.error_code.base.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ChatErrorCode implements ErrorCode {
    NO_CHAT_ROOM(HttpStatus.NOT_FOUND, "해당 채팅방을 찾을 수 없습니다"),
    NO_CHAT_MEMBER(HttpStatus.NOT_FOUND, "해당 채팅멤버를 찾을 수 없습니다"),
    NO_CHAT_MESSAGE(HttpStatus.NOT_FOUND, "해당 채팅내역을 찾을 수 없습니다");

    private final HttpStatus httpStatus;
    private final String message;
}
