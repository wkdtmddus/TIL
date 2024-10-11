package com.ssafy.ios.lineup.backend.application.facade.contract_request;

import static com.ssafy.ios.lineup.backend.common.constant.PayStatus.CASH_PAY;
import static com.ssafy.ios.lineup.backend.common.constant.PayStatus.CASH_REFUND;
import static com.ssafy.ios.lineup.backend.common.constant.error_code.UserCashErrorCode.LACK_OF_CASH;
import static com.ssafy.ios.lineup.backend.common.constant.error_code.UserErrorCode.NO_REQUIRED_USER_IN_CONTRACT_REQUEST;

import com.ssafy.ios.lineup.backend.application.service.alert.NotificationService;
import com.ssafy.ios.lineup.backend.application.service.application.ApplicationService;
import com.ssafy.ios.lineup.backend.application.service.contract_request.ContractRequestService;
import com.ssafy.ios.lineup.backend.application.service.pay.TransferDetailService;
import com.ssafy.ios.lineup.backend.application.service.pay.UserCashService;
import com.ssafy.ios.lineup.backend.application.service.recruit.RecruitService;
import com.ssafy.ios.lineup.backend.application.service.user.UserService;
import com.ssafy.ios.lineup.backend.common.constant.ContractRequestStatus;
import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.common.util.FileUtil;
import com.ssafy.ios.lineup.backend.common.validator.UserValidator;
import com.ssafy.ios.lineup.backend.domain.dto.contract_request.ContractRequestResponseDto;
import com.ssafy.ios.lineup.backend.domain.entity.Application;
import com.ssafy.ios.lineup.backend.domain.entity.ContractRequest;
import com.ssafy.ios.lineup.backend.domain.entity.Recruit;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/* 계약 요청 처리하는 Facade */
@Slf4j
@Service
@RequiredArgsConstructor
public class ContractRequestFacadeImpl implements ContractRequestFacade {

    private final ContractRequestService contractRequestService;
    private final TransferDetailService transferDetailService;
    private final NotificationService notificationService;
    private final ApplicationService applicationService;
    private final UserCashService userCashService;
    private final RecruitService recruitService;
    private final UserValidator userValidator;
    private final UserService userService;
    private final FileUtil fileUtil;

    /* 갑이 을에게 계약요청 전송 */
    @Override
    @Transactional // 생성되고 실패하면 생성 취소해야 하므로
    public Long sendContractRequestByRecruitIdAndApplicantId(
            Long recruitId,
            Long applicantId
    ) throws CustomException {
        Recruit recruit = recruitService.selectRecruitByIdAndNull(recruitId);
        Application application = applicationService.findApplication(recruitId,
                applicantId);

        User loginUser = userService.selectLoginUser();
        userValidator.checkLoginUserNonNull(loginUser);

        userValidator.checkWriter(loginUser, recruit);
//        contractRequestService.checkRecruitHasContractRequest(recruit);

        // 계약 요청 생성
        ContractRequest contractRequest = contractRequestService.createContractRequest(application);

        // 을에게 "계약 요청" 알림 보내기
        notificationService.notifyContractRequestReceive(contractRequest);
        return contractRequest.getId();
    }

    /*
     * 을이 갑의 계약요청 수락
     * */
    @Override
    @Transactional
    public void confirmContractRequestByContractee(Long recruitId, MultipartFile signature)
            throws CustomException {
        User loginUser = userService.selectLoginUser();
        userValidator.checkLoginUserNonNull(loginUser);
//        ContractRequest contractRequest = contractRequestService.selectContractRequestById(
//                contractRequestId);
        // TODO : 을인지 화인
        Application application = applicationService.findApplication(recruitId, loginUser.getId());
        ContractRequest contractRequest = contractRequestService.selectContractRequestByApplication(
                application);
        userValidator.checkApplicant(loginUser, contractRequest);

        // 계약 요청에 사인 저장
        contractRequestService.saveContracteeSignature(contractRequest, signature);

        // 페이 로직
        int amount = contractRequest.getApplication().getRecruit().getContracteeDeposit();
        if (amount > userCashService.getCashByUser(loginUser)) {
            throw new CustomException(LACK_OF_CASH);
        }
        transferDetailService.createTransferDetail(loginUser, amount, CASH_PAY);
        // 잔액 동기화
        userCashService.updateCashByUserAndUpdatedCash(
                loginUser,
                transferDetailService.calculateUserCash(loginUser)
        );
        // 계약요청 상태 변경
        contractRequestService.changeContractRequestStatusToAccept(contractRequest);
        // 갑에게 "계약 요청 수락" 알림 보내기
        notificationService.notifyContractRequestAccept(contractRequest);
        // TODO: 갑이 "계약 요청 수락" 이후, Contract 생성하면서, Verification 출근, 구매, 퇴근 3개가 생성됨.
    }

    /*
     * 계약요청 취소 or 거절
     * */
    @Override
    @Transactional
    public void cancelContractRequest(Long contractRequestId) throws CustomException {
        User loginUser = userService.selectLoginUser();
        userValidator.checkLoginUserNonNull(loginUser);

        ContractRequest contractRequest = contractRequestService.selectContractRequestById(
                contractRequestId);

        if (userValidator.isWriter(loginUser, contractRequest.getApplication().getRecruit())) {
            cancelContractRequestByWriter(contractRequest);
        } else if (userValidator.isApplicant(loginUser, contractRequest)) {
            rejectContractRequestByApplicant(contractRequest);
        } else {
            throw new IllegalArgumentException(NO_REQUIRED_USER_IN_CONTRACT_REQUEST.getMessage());
        }
    }

    /*
     *  계약 정보 찾아서 변환
     * */
    @Override
    public ContractRequestResponseDto searchContractRequestById(Long contractRequestId) {
        ContractRequest contractRequest = contractRequestService.selectContractRequestById(
                contractRequestId);
        return ContractRequestResponseDto.builder()
                .recruitTitle(
                        contractRequest.getApplication()
                                .getRecruit()
                                .getTitle())
                .recruitContent(
                        contractRequest.getApplication()
                                .getRecruit()
                                .getContent())
                .recruitImgUrl(
                        fileUtil.getRecruitImg(
                                contractRequest.getApplication()
                                        .getRecruit()
                                        .getRecruitImg()))
                .streetAddress(
                        contractRequest.getApplication()
                                .getRecruit().getLocation()
                                .getStreetAddress())
                .district(
                        contractRequest.getApplication()
                                .getRecruit()
                                .getLocation()
                                .getDistrict())
                .latitude(
                        contractRequest
                                .getApplication()
                                .getRecruit()
                                .getLocation()
                                .getLatitude())
                .longitude(
                        contractRequest.getApplication()
                                .getRecruit()
                                .getLocation()
                                .getLongitude())
                .contractorNickname(
                        contractRequest.getApplication()
                                .getRecruit()
                                .getWriter()
                                .getNickname())
                .contracteeNickname(
                        contractRequest.getApplication()
                                .getApplicant()
                                .getNickname())
//                .contractorSignatureImgUrl()
                .contracteeSignatureImgUrl(
                        fileUtil.getSignatureImg(
                                contractRequest.getContracteeSignatureFilename()
                        ))
                .build();
    }

    /* 갑이 계약요청 취소 */
    private void cancelContractRequestByWriter(ContractRequest contractRequest)
            throws CustomException {
        ContractRequestStatus contractRequestStatus = contractRequestService.getStatus(
                contractRequest);
        switch (contractRequestStatus) {
            case WAITING: // 아무도 결제 안 한 상태 , 계약 요청 삭제
                contractRequestService.deleteContractRequest(contractRequest);
                // TODO : 을에게 "계약 요청 취소" 알림 보내기
                break;
            case ACCEPT: // 지원자가 계약 요청 수락한 상태 & 지원자만 결제 완료
                // 지원자가 결제한 금액 돌려주기
                transferDetailService.createTransferDetail(
                        contractRequest.getApplication().getApplicant(),
                        contractRequest.getApplication().getRecruit().getContracteeDeposit(),
                        CASH_REFUND
                );
                // 계약 요청 삭제
                contractRequestService.deleteContractRequest(contractRequest);
                // TODO : 을에게 "계약 요청 취소" 알림 보내기
                break;
        }

    }

    /* 을이 계약요청 거절 */
    private void rejectContractRequestByApplicant(ContractRequest contractRequest)
            throws CustomException {
        ContractRequestStatus contractRequestStatus = contractRequestService.getStatus(
                contractRequest);

        switch (contractRequestStatus) {
            case WAITING: // 아무도 결제 안 한 상태 , 계약 요청 삭제
                contractRequestService.deleteContractRequest(contractRequest);
                // TODO : 갑에게 "계약 요청 취소" 알림 보내기
                break;
            case ACCEPT: // 지원자가 계약 요청 수락한 상태 & 지원자만 결제 완료
                // 지원자가 결제한 금액 돌려주기
                transferDetailService.createTransferDetail(
                        contractRequest.getApplication().getApplicant(),
                        contractRequest.getApplication().getRecruit().getContracteeDeposit(),
                        CASH_REFUND
                );
                // 계약 요청 삭제
                contractRequestService.deleteContractRequest(contractRequest);
                // TODO : 갑에게 "계약 요청 취소" 알림 보내기
                break;
        }
    }


}
