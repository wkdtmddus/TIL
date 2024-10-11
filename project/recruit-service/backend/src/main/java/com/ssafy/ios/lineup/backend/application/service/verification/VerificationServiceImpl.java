package com.ssafy.ios.lineup.backend.application.service.verification;

import com.nimbusds.jose.shaded.gson.Gson;
import com.ssafy.ios.lineup.backend.common.constant.RecruitStatus;
import com.ssafy.ios.lineup.backend.common.constant.ServiceType;
import com.ssafy.ios.lineup.backend.common.constant.VerificationType;
import com.ssafy.ios.lineup.backend.common.util.FileUtil;
import com.ssafy.ios.lineup.backend.domain.entity.*;
import com.ssafy.ios.lineup.backend.domain.repository.verification.VerificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {

    private final VerificationRepository verificationRepository;
    private final FileUtil fileUtil;
    private final Gson gson = new Gson();
    private static final Float ACCEPTABLE_DISTANCE = 0.5F;  // 0.5km 이내

    @Override
    public boolean verifyQRCode(String qrCodeData, Long expectedRecruitId, String expectedContractUuid) {
        // 1. QR 코드 데이터를 파싱하여 Map으로 변환
        Map<String, Object> qrData = gson.fromJson(qrCodeData, Map.class);

        // 2. QR 코드에 포함된 recruitId, contractUuid 확인
        Long recruitId = ((Double) qrData.get("recruitId")).longValue();  // JSON에서 Double로 변환됨
        String contractUuid = qrData.get("contractUuid").toString();

        // 3. 기대하는 값과 비교하여 검증
        return recruitId.equals(expectedRecruitId) && contractUuid.equals(expectedContractUuid);
    }

    @Override
    public Verification selectVerificationToVerificationType(Contract contract, VerificationType verificationType) {
        return verificationRepository.findByContractAndVerificationType(contract, verificationType);
    }

    @Transactional
    @Override
    public Verification createPhotoVerification(Verification verification, MultipartFile verificationImg) {
        if (verificationImg != null && !verificationImg.isEmpty()) {
            String uuid = UUID.randomUUID().toString();
            fileUtil.saveVerificationImg(verificationImg, uuid); // 인증 사진 저장

            verification.updatePhotoUrl(
                    uuid + "." + fileUtil.getExtension(verificationImg));

            // 사진 인증 완료
            verification.updatePhotoVerified(true);
        }
        return verification;
    }

    @Override
    public Verification createGPSVerification(Verification verification, Float latitude, Float longitude) {
        Recruit recruit = verification.getContract().getRecruit();
        // GPS 위치 확인
        boolean isWithinRange = isWithinGeofence(recruit, latitude, longitude);
        if (!isWithinRange) {
            log.info("GPS 인증 실패: 지정된 위치가 아닙니다.");
        }

        verification.updateLocation(latitude, longitude);
        // GPS 인증 완료
        verification.updateGPSVerified(true);

        return verification;
    }

    public boolean isWithinGeofence(Recruit recruit, Float latitude, Float longitude) {
        Float distance = calculateDistance(recruit.getLocation().getLatitude(), recruit.getLocation().getLongitude(), latitude, longitude);
        return distance <= ACCEPTABLE_DISTANCE;
    }

    /* 두 좌표 간 거리 계산 (단위: km) */
    public Float calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // 지구 반지름 (km)
        Float latDistance = (float) Math.toRadians(lat2 - lat1);
        Float lonDistance = (float) Math.toRadians(lon2 - lon1);
        Float a = (float) (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2));
        Float c = (float) (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)));
        return R * c;
    }
}
