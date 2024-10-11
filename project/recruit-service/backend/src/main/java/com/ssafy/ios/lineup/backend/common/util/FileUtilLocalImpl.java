package com.ssafy.ios.lineup.backend.common.util;

import static com.ssafy.ios.lineup.backend.common.constant.error_code.FileErrorCode.CANNOT_CONVERT_FILE;
import static com.ssafy.ios.lineup.backend.common.constant.error_code.FileErrorCode.CANNOT_DELETE_FILE;
import static com.ssafy.ios.lineup.backend.common.constant.error_code.FileErrorCode.CANNOT_FIND_EXT_IN_FILE;
import static com.ssafy.ios.lineup.backend.common.constant.error_code.FileErrorCode.CANNOT_GET_FILE;
import static com.ssafy.ios.lineup.backend.common.constant.error_code.FileErrorCode.CANNOT_MAKE_DIRECTORY;
import static com.ssafy.ios.lineup.backend.common.constant.error_code.FileErrorCode.CANNOT_SAVE_FILE;
import static com.ssafy.ios.lineup.backend.common.constant.error_code.FileErrorCode.FILE_DELETION_ERROR;

import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.common.validator.FileValidator;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
//@Component
@RequiredArgsConstructor
// @Primary
public class FileUtilLocalImpl implements FileUtil {

    private final FileValidator fileValidator;

    @Value("${file.path.user-img}")
    private String userImgFolderPath;

    @Value("${file.path.signature-img}")
    private String signatureFolderPath;

    @Value("${file.path.message-img}")
    private String messageImgFolderPath;

    @Value("${file.path.recruit-img}")
    private String recruitImgFolderPath;

    @Value("${file.path.verification-img}")
    private String verificationImgFolderPath;

    public String saveUserProfileImg(MultipartFile file, String uuid)
            throws CustomException {
        fileValidator.checkImageExt(getExtension(file));
        saveFileByUuid(userImgFolderPath, file, uuid);
        return uuid;
    }

    public String saveSignatureImg(MultipartFile file, String uuid)
            throws CustomException {
        fileValidator.checkImageExt(getExtension(file));
        saveFileByUuid(signatureFolderPath, file, uuid);
        return uuid;
    }

    public String saveMessageImg(MultipartFile file, String uuid)
            throws CustomException {
        fileValidator.checkImageExt(getExtension(file));
        saveFileByUuid(messageImgFolderPath, file, uuid);
        return uuid;
    }

    public String saveRecruitImg(MultipartFile file, String uuid)
            throws CustomException {
        fileValidator.checkImageExt(getExtension(file));
        log.info("확장자 설정 완료");
        saveFileByUuid(recruitImgFolderPath, file, uuid);
        return uuid;
    }

    @Override
    public String saveVerificationImg(MultipartFile file, String uuid) {
        fileValidator.checkImageExt(getExtension(file));
        log.info("확장자 설정 완료");
        saveFileByUuid(verificationImgFolderPath, file, uuid);
        return uuid;
    }

    /* 프론트엔드로 보내줄 때 base64로 인코딩해서 전달 */
    public String getUserProfileImg(String profileImgName) throws CustomException {
        // fileValidator.checkFileNonEmpty(profileImgName);
        Path path = Paths.get(userImgFolderPath, profileImgName);
        return getFileAsBase64(path);
    }

    /* 프론트엔드로 보내줄 때 base64로 인코딩해서 전달 */
    public String getRecruitImg(String recruitImgName) throws CustomException {
        // fileValidator.checkFileNonEmpty(recruitImgName);
        Path path = Paths.get(recruitImgFolderPath, recruitImgName);
        return getFileAsBase64(path);
    }

    @Override
    public String getSignatureImg(String signatureImgName) {
        Path path = Paths.get(signatureFolderPath, signatureImgName);
        return getFileAsBase64(path);
    }

    @Override
    public String getVerificationImg(String verificationImgName) throws CustomException {
        Path path = Paths.get(verificationImgFolderPath, verificationImgName);
        return getFileAsBase64(path);
    }

    public String getExtension(MultipartFile file) throws CustomException {
        String originalFilename = file.getOriginalFilename();
        fileValidator.checkFileNonEmpty(originalFilename);
        int index = originalFilename.lastIndexOf(".");
        if (index == -1) {
            throw new CustomException(CANNOT_FIND_EXT_IN_FILE);
        }
        return originalFilename.substring(index + 1);
    }

    /* 기존 공고 이미지를 수정한다면 기존의 존재하는 이미지는 삭제한다. */
    public void deleteFileImg(String filename) {
        fileValidator.checkFileNonEmpty(filename);

        try {
            // Path path = Paths.get(recruitImgFolderPath, filename);
            // File file = new File(recruitImgFolderPath, path.getFileName().toString());
            makeDirectoryIfNotExists(recruitImgFolderPath);
            // 상대 경로를 절대 경로로 변환
            String absolutePath = new File(recruitImgFolderPath).getAbsolutePath();
            File savedImg = new File(absolutePath, filename);
            log.info(absolutePath);
            log.info("{}", savedImg);
            if (savedImg.exists()) {
                boolean isDeleted = savedImg.delete();
                if (!isDeleted) {
                    throw new CustomException(CANNOT_DELETE_FILE);
                }
                //
            } else {
                throw new CustomException(CANNOT_DELETE_FILE);
            }

        } catch (Exception e) {
            throw new CustomException(FILE_DELETION_ERROR);
        }

    }

    private String getFileAsBase64(Path path) throws CustomException {
        try {
            return Base64.getEncoder().encodeToString(Files.readAllBytes(path));
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new CustomException(CANNOT_CONVERT_FILE);
        }
    }

    private void saveFileByUuid(String folderPath, MultipartFile file, String uuid)
            throws CustomException {
        makeDirectoryIfNotExists(folderPath);
        // 상대 경로를 절대 경로로 변환
        String directory = new File(folderPath).getAbsolutePath();
        String saveFileName = uuid + "." + getExtension(file);
        File savedImg = new File(directory, saveFileName);
        try {
            file.transferTo(savedImg);
        } catch (IOException e) {
            log.info("파일 transferTo 실패");
            log.error(e.getMessage());
            throw new CustomException(CANNOT_SAVE_FILE);
        }
    }

    private Resource downloadOneFile(String folderPath, String fileName)
            throws CustomException {
        try {
            UrlResource resource = new UrlResource(
                    Paths.get(folderPath, fileName).toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            throw new MalformedURLException();
        } catch (MalformedURLException e) {
            throw new CustomException(CANNOT_GET_FILE);
        }
    }

    private void makeDirectoryIfNotExists(String folderPath) throws CustomException {
        File directory = new File(folderPath);
        if (!directory.exists() && !directory.mkdirs()) {
            throw new CustomException(CANNOT_MAKE_DIRECTORY);
        }
    }
}
