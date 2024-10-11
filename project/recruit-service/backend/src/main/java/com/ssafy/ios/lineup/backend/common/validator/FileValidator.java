package com.ssafy.ios.lineup.backend.common.validator;

import static com.ssafy.ios.lineup.backend.common.constant.error_code.FileErrorCode.CANNOT_FIND_FILE;
import static com.ssafy.ios.lineup.backend.common.constant.error_code.FileErrorCode.NOT_IMG_EXT_FILE;

import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import org.springframework.stereotype.Component;

@Component
public class FileValidator {

    public void checkImageExt(String ext) throws CustomException {
        if (!ext.equals("png") && !ext.equals("jpg") && !ext.equals("jpeg")) {
            throw new CustomException(NOT_IMG_EXT_FILE);
        }
    }

    public void checkFileNonEmpty(String fileName) throws CustomException {
        if (fileName == null || fileName.isEmpty()) {
            throw new CustomException(
                    CANNOT_FIND_FILE);
        }
    }
}
