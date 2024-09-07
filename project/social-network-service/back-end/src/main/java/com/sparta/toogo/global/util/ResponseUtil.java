package com.sparta.toogo.global.util;

import com.sparta.toogo.global.enums.ErrorCode;
import com.sparta.toogo.global.enums.SuccessCode;
import com.sparta.toogo.global.responsedto.ApiResponse;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class ResponseUtil {

    public static <T> ApiResponse<T> ok(T response) {
        return new ApiResponse<>(true, 200, null, response, null);
    }

    public static ApiResponse<?> ok(SuccessCode successCode) {
        int statusCode = successCode.getHttpStatus().value();
        String msg = successCode.getDetail();
        return new ApiResponse<>(true, statusCode, msg, null, null);
    }

    public static ApiResponse<?> error(ErrorCode errorCode) {
        int statusCode = errorCode.getHttpStatus().value();
        String msg = errorCode.getDetail();
        return new ApiResponse<>(false, statusCode, msg, null, null);
    }

    public static ApiResponse<?> error(HttpStatus httpStatus, Map<String, String> errors) {
        return new ApiResponse<>(false, httpStatus.value(), null, null, errors);
    }
}