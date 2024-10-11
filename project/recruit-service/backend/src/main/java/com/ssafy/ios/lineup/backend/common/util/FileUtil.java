package com.ssafy.ios.lineup.backend.common.util;

import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import org.springframework.web.multipart.MultipartFile;

public interface FileUtil {

    String saveUserProfileImg(MultipartFile file, String uuid);

    String saveSignatureImg(MultipartFile file, String uuid);

    String saveMessageImg(MultipartFile file, String uuid);

    String saveRecruitImg(MultipartFile file, String uuid);

    String saveVerificationImg(MultipartFile file, String uuid);

//    String saveWorkStartImg(MultipartFile file, String uuid);

//    String saveWorkPurchaseImg(MultipartFile file, String uuid);

    /* 프론트엔드로 보내줄 때 base64로 인코딩해서 전달 */
    String getUserProfileImg(String profileImgName) throws CustomException;

    /* 프론트엔드로 보내줄 때 base64로 인코딩해서 전달 */
    String getRecruitImg(String recruitImgName) throws CustomException;

    String getSignatureImg(String signatureImgName);

    String getVerificationImg(String verificationImgName) throws CustomException;

//    String getWorkStartImg(String recruitImgName) throws CustomException;

//    String getWorkPurchaseImg(String recruitImgName) throws CustomException;


    String getExtension(MultipartFile file) throws CustomException;

    /* 기존 공고 이미지를 수정한다면 기존의 존재하는 이미지는 삭제한다. */
    void deleteFileImg(String filename);


}
