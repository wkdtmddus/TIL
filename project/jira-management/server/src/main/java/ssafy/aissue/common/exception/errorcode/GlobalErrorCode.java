package ssafy.aissue.common.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static ssafy.aissue.api.StatusCode.*;


@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements BaseErrorCode {

    BAD_REQUEST_ERROR(BAD_REQUEST, "GLOBAL_400_1", "잘못된 요청입니다."),
    INVALID_HTTP_MESSAGE_BODY(BAD_REQUEST, "GLOBAL_400_2", "HTTP 요청 바디의 형식이 잘못되었습니다."),
    ACCESS_DENIED_REQUEST(FORBIDDEN, "GLOBAL_403_3", "해당 요청에 접근 권한이 없습니다."),
    UNSUPPORTED_HTTP_METHOD(METHOD_NOT_ALLOWED, "GLOBAL_405", "지원하지 않는 HTTP 메서드입니다."),
    SERVER_ERROR(INTERNAL_SERVER_ERROR, "GLOBAL_500", "서버 내부에서 알 수 없는 오류가 발생했습니다."),

    PUBKEY_GENERATION_FAILED(BAD_REQUEST, "PUBLIC_KEY_400_1", "공개키를 생성하는데 실패했습니다."),

    SORT_TYPE_NOT_MATCHED(BAD_REQUEST, "SORT_400", "해당 조건으로 정렬할 수 없습니다.");


    private final Integer httpStatus;
    private final String code;
    private final String message;
}
