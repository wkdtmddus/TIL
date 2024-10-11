package com.ssafy.ios.lineup.backend.application.facade.Verification;

import com.ssafy.ios.lineup.backend.application.service.application.ApplicationService;
import com.ssafy.ios.lineup.backend.application.service.auth.AuthService;
import com.ssafy.ios.lineup.backend.application.service.contract.ContractService;
import com.ssafy.ios.lineup.backend.application.service.recruit.RecruitService;
import com.ssafy.ios.lineup.backend.application.service.user.UserService;
import com.ssafy.ios.lineup.backend.application.service.verification.VerificationService;
import com.ssafy.ios.lineup.backend.common.constant.ContractStatus;
import com.ssafy.ios.lineup.backend.common.constant.VerificationType;
import com.ssafy.ios.lineup.backend.common.util.QrCodeUtil;
import com.ssafy.ios.lineup.backend.common.validator.UserValidator;
import com.ssafy.ios.lineup.backend.domain.entity.*;

import com.ssafy.ios.lineup.backend.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class VerificationFacadeImpl implements VerificationFacade {

    private final UserService userService;
    private final VerificationService verificationService;
    private final UserValidator userValidator;
    private final ContractService contractService;
    private final QrCodeUtil qrCodeUtil;

    /**
     * 출근 인증 처리 (공고자와 대행자 통합)
     */
    @Transactional
    @Override
    public boolean verifyCheckIn(Long contractId, MultipartFile photoImg, Float latitude, Float longitude) {
        User loginUser = userService.selectLoginUser();
        userValidator.checkUserNonNull(loginUser);

        Contract contract = contractService.selectContractById(contractId);
        Verification verification = verificationService.selectVerificationToVerificationType(contract, VerificationType.CHECK_IN);

        // 공고자인지 대행자인지 확인
        boolean isRecruiter = contract.getRecruit().getWriter().equals(loginUser);

        if (isRecruiter) {
            return handleContractorCheckInStatus(verification);
        } else {
            return handleContracteeCheckIn(verification, photoImg, latitude, longitude);
        }
    }

    /**
     * 구매 인증 처리 (공고자와 대행자 통합)
     */
    @Transactional
    @Override
    public boolean verifyPurchase(Long contractId, MultipartFile photoImg) {
        User loginUser = userService.selectLoginUser();
        userValidator.checkUserNonNull(loginUser);

        Contract contract = contractService.selectContractById(contractId);
        Verification verification = verificationService.selectVerificationToVerificationType(contract, VerificationType.PURCHASE);

        // 공고자인지 대행자인지 확인
        boolean isRecruiter = contract.getRecruit().getWriter().equals(loginUser);

        if (isRecruiter) {
            return handleContractorPurchaseStatus(verification);
        } else {
            return handleContracteePurchase(verification, photoImg);
        }
    }

    /**
     * 퇴근 인증 처리 (공고자와 대행자 통합)
     */
    @Transactional
    @Override
    public Contract verifyCheckOut(Long contractId) {
        User loginUser = userService.selectLoginUser();
        userValidator.checkUserNonNull(loginUser);

        Contract contract = contractService.selectContractById(contractId);
        Verification verification = verificationService.selectVerificationToVerificationType(contract, VerificationType.CHECK_OUT);

        boolean isRecruiter = contract.getRecruit().getWriter().equals(loginUser);

        if (isRecruiter) {
            handleContractorCheckOut(contract, verification);
        } else {
            handleContracteeCheckOut(contract, verification);
        }

        return contract;
    }

    // 대행자 출근 인증 처리
    private boolean handleContracteeCheckIn(Verification verification, MultipartFile photoImg, Float latitude, Float longitude) {
        if (latitude != null && longitude != null) {
            verificationService.createGPSVerification(verification, latitude, longitude);
        }

        if (photoImg != null && !photoImg.isEmpty()) {
            verificationService.createPhotoVerification(verification, photoImg);
        }

        // 둘 다 인증이 완료된 경우
        if (verification.isPhotoVerified() && verification.isGPSVerified()) {
            // QR 코드 생성 로직 추가
            generateQrCodeAfterCheckInOrPurchase(verification.getContract().getId());
            return true;
        }
        return false;
    }

    // 공고자 출근 인증 상태 확인
    private boolean handleContractorCheckInStatus(Verification verification) {
        return isCheckInComplete(verification);
    }

    // 출근 인증 완료 여부 확인
    private boolean isCheckInComplete(Verification verification) {
        return verification.isGPSVerified() && verification.isPhotoVerified();
    }

    // 대행자 구매 인증 처리
    private boolean handleContracteePurchase(Verification verification, MultipartFile photoImg) {
        if (photoImg != null && !photoImg.isEmpty()) {
            verificationService.createPhotoVerification(verification, photoImg);
        }

        if (verification.isPhotoVerified()) {
            // 구매 인증 완료 후 QR 코드 생성
            generateQrCodeAfterCheckInOrPurchase(verification.getContract().getId());
            return true;
        }
        return false;
    }

    private boolean handleContractorPurchaseStatus(Verification verification) {
        return verification.isPhotoVerified();
    }

    // 공고자 퇴근 인증 처리
    private void handleContractorCheckOut(Contract contract, Verification verification) {
        if (verification.isVerified()) {
            updateContractStatusOnCheckOut(contract);
        } else {
            log.info("대행자가 아직 퇴근 인증을 완료하지 않았습니다.");
        }
    }

    // 대행자 퇴근 인증 처리
    private void handleContracteeCheckOut(Contract contract, Verification verification) {
        if (verificationService.verifyQRCode(verification.getQrCodeContent(), contract.getRecruit().getId(), contract.getContractUuid())) {
            verification.updateVerified(true);
            log.info("대행자가 퇴근 인증을 성공하였습니다.");
        } else {
            log.info("QR 코드 인증에 실패했습니다. 유효한 QR 코드를 입력해 주세요.");
        }
    }

    // 계약 상태 업데이트 (퇴근 인증 완료 시)
    private void updateContractStatusOnCheckOut(Contract contract) {
        if (contract.getRecruit().getServiceType().getDbValue().equals("QUEUE")) {
            contract.updateContractStatus(ContractStatus.WORK_END_QUEUE_VERIFICATION_SUCCESS);
        } else {
            contract.updateContractStatus(ContractStatus.WORK_END_PURCHASE_VERIFICATION_SUCCESS);
        }
    }

    /**
     * 출근 인증 또는 구매 인증 완료 후 QR 코드 생성 및 저장
     */
    private void generateQrCodeAfterCheckInOrPurchase(Long contractId) {
        Contract contract = contractService.selectContractById(contractId);
        Verification verification = verificationService.selectVerificationToVerificationType(contract, VerificationType.CHECK_OUT);

        // 이미 QR 코드가 생성된 경우, 다시 생성하지 않음
        if (verification.getQrCodeContent() != null) {
            log.info("이미 QR 코드가 생성된 상태입니다.");
            return;
        }

        // QR 코드에 포함될 정보 구성
        String nickname = contract.getRecruit().getWriter().getNickname();
        Long recruitId = contract.getRecruit().getId();
        String contractUuid = contract.getContractUuid();

        // QR 코드 생성
        String qrCodeContent = qrCodeUtil.generateQRCodeWithInfo(nickname, recruitId, verification.getId(), contractUuid);
        verification.updateQrCodeContent(qrCodeContent);

        log.info("QR 코드 생성 및 저장 완료: {}", qrCodeContent);
    }

    @Transactional
    public String getQrCodeForContractor(Long contractId) {
        // 로그인한 사용자가 공고자인지 확인
        User loginUser = userService.selectLoginUser();
        userValidator.checkUserNonNull(loginUser);

        // 계약 정보 및 공고 정보 조회
        Contract contract = contractService.selectContractById(contractId);
        Recruit recruit = contract.getRecruit();
        userValidator.checkWriter(loginUser, recruit);

        // Verification 정보 조회 (퇴근 인증용 QR 코드)
        Verification verification = verificationService.selectVerificationToVerificationType(contract, VerificationType.CHECK_OUT);

        // QR 코드가 생성되어 있지 않으면 생성 후 반환
        if (verification.getQrCodeContent() == null) {
            String qrCodeContent = qrCodeUtil.generateQRCodeWithInfo(
                    loginUser.getNickname(), contract.getRecruit().getId(), verification.getId(), contract.getContractUuid()
            );
            verification.updateQrCodeContent(qrCodeContent);
            return qrCodeContent;
        }
        // 이미 생성된 QR 코드가 있으면 해당 내용을 반환
        return verification.getQrCodeContent();
    }
}
