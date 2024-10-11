package com.ssafy.ios.lineup.backend.application.service.verification;

import com.ssafy.ios.lineup.backend.common.constant.ServiceType;
import com.ssafy.ios.lineup.backend.common.constant.VerificationType;
import com.ssafy.ios.lineup.backend.domain.entity.*;
import org.springframework.web.multipart.MultipartFile;

public interface VerificationService {

    /* QR 코드 검증 */
    public boolean verifyQRCode(String qrCodeData, Long expectedRecruitId, String expectedContractUuid);

    /* 인증 타입에 따른 인증 조회 */
    public Verification selectVerificationToVerificationType(Contract contract, VerificationType verificationType);

    /* 사진 인증 */
    Verification createPhotoVerification(Verification verification, MultipartFile verificationImg);

    /* GPS 인증 */
    Verification createGPSVerification(Verification verification, Float latitude, Float longitude);

    /* GPS 이탈 여부 판단 */
    boolean isWithinGeofence(Recruit recruit, Float latitude, Float longitude);

    /* 두 좌표간 거리 계산 */
    Float calculateDistance(double lat1, double lon1, double lat2, double lon2);
}
