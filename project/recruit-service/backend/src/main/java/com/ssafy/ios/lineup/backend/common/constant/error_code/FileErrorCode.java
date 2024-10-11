package com.ssafy.ios.lineup.backend.common.constant.error_code;

import com.ssafy.ios.lineup.backend.common.constant.error_code.base.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FileErrorCode implements ErrorCode {
    NOT_IMG_EXT_FILE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "이미지 확장지의 파일이 아닙니다"),
    CANNOT_FIND_FILE(HttpStatus.NOT_FOUND, "해당하는 파일을 찾을 수 없습니다"),
    CANNOT_SAVE_FILE(HttpStatus.BAD_REQUEST, "파일을 저장할 수 없습니다"),
    CANNOT_CONVERT_FILE(HttpStatus.BAD_REQUEST, "파일을 변환할 수 없습니다"),
    CANNOT_GET_FILE(HttpStatus.BAD_REQUEST, "파일을 가져올 수 없습니다"),
    CANNOT_MAKE_DIRECTORY(HttpStatus.BAD_REQUEST, "파일 저장 디렉토리 생성에 실패했습니다"),
    CANNOT_FIND_EXT_IN_FILE(HttpStatus.BAD_REQUEST, "파일의 확장자가 없습니다"),
    CANNOT_DELETE_FILE(HttpStatus.BAD_REQUEST, "파일을 삭제할 수 없습니다"),
    FILE_DELETION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "파일 삭제 중 오류가 발생했습니다"),
    CANNOT_CONVERT_MULTIPART_TO_FILE(HttpStatus.INTERNAL_SERVER_ERROR,
            "MultipartFile -> File 전환 실패"),
    EMPTY_FILENAME(HttpStatus.INTERNAL_SERVER_ERROR, "빈 파일 이름입니다."),
    CANNOT_CREATE_NEW_FILE(HttpStatus.INTERNAL_SERVER_ERROR, "빈 파일을 생성할 수 없습니다");

    private final HttpStatus httpStatus;
    private final String message;

}
