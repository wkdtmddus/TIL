package ssafy.aissue.common.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ssafy.aissue.api.CommonResponse;
import ssafy.aissue.common.exception.member.AbnormalLoginProgressException;
import ssafy.aissue.common.exception.member.AlreadyExistingMemberException;
import ssafy.aissue.common.exception.security.FilterException;
import ssafy.aissue.common.exception.security.IssuerTokenIncorrectException;
import ssafy.aissue.common.exception.security.NotAuthenticatedException;
import ssafy.aissue.common.exception.security.TokenExpiredException;
import ssafy.aissue.common.exception.token.TokenTypeNotMatchedException;


@RestControllerAdvice
@Slf4j
public class AuthExceptionHandler {
    @ExceptionHandler(IssuerTokenIncorrectException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CommonResponse handleIncorrectIssuerTokenException(IssuerTokenIncorrectException e) {
        log.error("IssuerTokenIncorrectException", e);
        return CommonResponse.unauthorized(e.getErrorCode());
    }

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CommonResponse handleExpiredTokenException(TokenExpiredException e) {
        log.error("TokenExpiredException", e);
        return CommonResponse.unauthorized(e.getErrorCode());
    }

    @ExceptionHandler(TokenTypeNotMatchedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse handleNotMatchedTokenTypeException(TokenTypeNotMatchedException e) {
        log.error("TokenTypeNotMatchedException", e);
        return CommonResponse.unauthorized(e.getErrorCode());
    }

    @ExceptionHandler(FilterException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResponse handleFilterErrorException(FilterException e) {
        log.error("FilterException", e);
        return CommonResponse.internalServerError(e.getErrorCode());
    }

    @ExceptionHandler(AbnormalLoginProgressException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResponse handleAbnormalLoginProgressException(AbnormalLoginProgressException e) {
        log.error("AbnormalLoginProgressException", e);
        return CommonResponse.internalServerError(e.getErrorCode());
    }


    @ExceptionHandler(NotAuthenticatedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CommonResponse handleNotAuthenticatedException(NotAuthenticatedException e) {
        log.error("NotAuthenticatedException", e);
        return CommonResponse.unauthorized(e.getErrorCode());
    }

    @ExceptionHandler(AlreadyExistingMemberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse handleAlreadyExistingMemberException(AlreadyExistingMemberException e) {
        log.error("AlreadyExistingMemberException", e);
        return CommonResponse.badRequest(e.getErrorCode());
    }

}
