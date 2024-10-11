package com.ssafy.ios.lineup.backend.common.constant.error_code.base;

import org.springframework.http.HttpStatus;


public interface ErrorCode {

    HttpStatus getHttpStatus();

    String getMessage();
}
