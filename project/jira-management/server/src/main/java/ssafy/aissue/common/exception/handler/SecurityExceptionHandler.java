package ssafy.aissue.common.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ssafy.aissue.api.CommonResponse;
import ssafy.aissue.common.exception.security.InvalidPasswordException;
import ssafy.aissue.common.exception.security.PasswordUsedException;

@RestControllerAdvice
@Slf4j
public class SecurityExceptionHandler {
    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse handleInvalidPasswordException(InvalidPasswordException e) {
        log.error("InvalidPasswordException Error", e);
        return CommonResponse.badRequest(e.getErrorCode());
    }

    @ExceptionHandler(PasswordUsedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public CommonResponse handlePasswordUsedException(PasswordUsedException e) {
        log.error("PasswordUsedException Error", e);
        return CommonResponse.conflict(e.getErrorCode());
    }
}