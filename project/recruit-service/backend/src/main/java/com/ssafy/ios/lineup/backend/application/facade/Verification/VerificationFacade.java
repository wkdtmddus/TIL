package com.ssafy.ios.lineup.backend.application.facade.Verification;

import com.ssafy.ios.lineup.backend.domain.entity.Contract;
import org.springframework.web.multipart.MultipartFile;

public interface VerificationFacade {
    // 출근 인증
    boolean verifyCheckIn(Long contractId, MultipartFile photoImg, Float latitude, Float longitude);

    // 구매 인증
    boolean verifyPurchase(Long contractId, MultipartFile photoImg);

    // 퇴근 인증
    Contract verifyCheckOut(Long contractId);

    // QR 코드 반환
    public String getQrCodeForContractor(Long contractId);

}
