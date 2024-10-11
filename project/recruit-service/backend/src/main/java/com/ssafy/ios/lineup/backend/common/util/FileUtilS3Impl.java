package com.ssafy.ios.lineup.backend.common.util;

import static com.ssafy.ios.lineup.backend.common.constant.error_code.FileErrorCode.CANNOT_CONVERT_MULTIPART_TO_FILE;
import static com.ssafy.ios.lineup.backend.common.constant.error_code.FileErrorCode.CANNOT_CREATE_NEW_FILE;
import static com.ssafy.ios.lineup.backend.common.constant.error_code.FileErrorCode.CANNOT_FIND_EXT_IN_FILE;
import static com.ssafy.ios.lineup.backend.common.constant.error_code.FileErrorCode.EMPTY_FILENAME;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.common.validator.FileValidator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileUtilS3Impl implements FileUtil {

    private final AmazonS3 amazonS3Client;
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

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public String saveUserProfileImg(MultipartFile file, String uuid) throws CustomException {
        String ext = getExtension(file);
        return upload(convert(file), userImgFolderPath, uuid, ext);
    }

    @Override
    public String saveSignatureImg(MultipartFile file, String uuid) throws CustomException {
        String ext = getExtension(file);
        return upload(convert(file), signatureFolderPath, uuid, ext);
    }

    @Override
    public String saveMessageImg(MultipartFile file, String uuid) throws CustomException {
        String ext = getExtension(file);
        return upload(convert(file), messageImgFolderPath, uuid, ext);
    }

    @Override
    public String saveRecruitImg(MultipartFile file, String uuid) throws CustomException {
        String ext = getExtension(file);
        return upload(convert(file), recruitImgFolderPath, uuid, ext);
    }

    @Override
    public String saveVerificationImg(MultipartFile file, String uuid) {
        String ext = getExtension(file);
        return upload(convert(file), verificationImgFolderPath, uuid, ext);
    }

    @Override
    public String getUserProfileImg(String profileImgName) throws CustomException {
        // fileValidator.checkFileNonEmpty(profileImgName);
//        return amazonS3Client.getUrl(bucket, userImgFolderPath + "/" + profileImgName).toString();
        return "https://d269cl8sgzlqcm.cloudfront.net/images/" + userImgFolderPath + "/"
                + profileImgName;
    }

    @Override
    public String getRecruitImg(String recruitImgName) throws CustomException {
        // fileValidator.checkFileNonEmpty(recruitImgName);
//        return amazonS3Client.getUrl(bucket, recruitImgFolderPath + "/" + recruitImgName)
//                .toString();
        return "https://d269cl8sgzlqcm.cloudfront.net/images/" + recruitImgFolderPath + "/"
                + recruitImgName;
    }

    @Override
    public String getSignatureImg(String signatureImgName) {
        return "https://d269cl8sgzlqcm.cloudfront.net/images/" + signatureFolderPath + "/"
                + signatureImgName;
    }

    @Override
    public String getVerificationImg(String verificationImgName) throws CustomException {
//        return amazonS3Client.getUrl(bucket, verificationImgFolderPath + "/" + verificationImgName)
//                .toString();
        return "https://d269cl8sgzlqcm.cloudfront.net/images/" + verificationImgFolderPath + "/"
                + verificationImgName;
    }

    @Override
    public String getExtension(MultipartFile file) throws CustomException {
        String originalFilename = file.getOriginalFilename();
        fileValidator.checkFileNonEmpty(originalFilename);
        int index = originalFilename.lastIndexOf(".");
        if (index == -1) {
            throw new CustomException(CANNOT_FIND_EXT_IN_FILE);
        }
        return originalFilename.substring(index + 1);
    }

    @Override
    public void deleteFileImg(String filename) {

    }

    private String upload(File uploadFile, String dirName, String uuid, String ext)
            throws CustomException {
        String fileName = dirName + "/" + uuid + "." + ext;
        String uploadImageUrl = putS3(uploadFile, fileName);
        removeNewFile(uploadFile);

        return uploadImageUrl;
    }

    private void getFile(String fileName) {
        try {
            S3Object o = amazonS3Client.getObject(bucket, fileName);
            S3ObjectInputStream file = o.getObjectContent();
            FileOutputStream fos = new FileOutputStream(new File(fileName));
            byte[] read_buf = new byte[1024];
            int read_len = 0;
            while ((read_len = file.read(read_buf)) > 0) {
                fos.write(read_buf, 0, read_len);
            }
            file.close();
            fos.close();
        } catch (AmazonServiceException e) {
            log.error(e.getMessage());
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private String putS3(File uploadFile, String fileName) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(uploadFile.length());

        amazonS3Client.putObject(
                new PutObjectRequest(bucket, fileName, uploadFile)
                // .withCannedAcl(CannedAccessControlList.PublicRead)
        );
        log.info("S3에 파일 업로드 중: {}", fileName);
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

    private File convert(MultipartFile file) throws CustomException {
        String fileName = file.getOriginalFilename();
        if (fileName == null || fileName.isEmpty()) {
            throw new CustomException(EMPTY_FILENAME);
        }

        File convertFile = new File(file.getOriginalFilename());
        log.info("convertFile: {}", convertFile);

        try {
            if (convertFile.exists()) {
                log.info("File already exists: {}", convertFile.getName());
            } else if (!convertFile.createNewFile()) {
                throw new CustomException(CANNOT_CREATE_NEW_FILE);
            }
        } catch (IOException e) {
            log.error("Error creating new file: {}", e.getMessage());
            throw new CustomException(CANNOT_CONVERT_MULTIPART_TO_FILE);
        }

        try (FileOutputStream fos = new FileOutputStream(convertFile)) {
            fos.write(file.getBytes());
            return convertFile;
        } catch (IOException e) {
            throw new CustomException(CANNOT_CREATE_NEW_FILE);
        }
    }
}
