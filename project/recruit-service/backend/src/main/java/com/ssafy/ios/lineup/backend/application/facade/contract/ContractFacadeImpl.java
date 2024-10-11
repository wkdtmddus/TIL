package com.ssafy.ios.lineup.backend.application.facade.contract;

import static com.ssafy.ios.lineup.backend.common.constant.PayStatus.CASH_PAY;
import static com.ssafy.ios.lineup.backend.common.constant.PayStatus.CASH_REFUND;
import static com.ssafy.ios.lineup.backend.common.constant.error_code.UserCashErrorCode.LACK_OF_CASH;
import static com.ssafy.ios.lineup.backend.common.constant.error_code.UserErrorCode.NO_REQUIRED_USER_IN_CONTRACT;

import com.ssafy.ios.lineup.backend.application.service.alert.NotificationService;
import com.ssafy.ios.lineup.backend.application.service.contract.ContractService;
import com.ssafy.ios.lineup.backend.application.service.contract_request.ContractRequestService;
import com.ssafy.ios.lineup.backend.application.service.pay.TransferDetailService;
import com.ssafy.ios.lineup.backend.application.service.pay.UserCashService;
import com.ssafy.ios.lineup.backend.application.service.recruit.RecruitService;
import com.ssafy.ios.lineup.backend.application.service.user.UserService;
import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.common.validator.UserValidator;
import com.ssafy.ios.lineup.backend.domain.dto.contract.ContractFilter;
import com.ssafy.ios.lineup.backend.domain.entity.Contract;
import com.ssafy.ios.lineup.backend.domain.entity.ContractRequest;
import com.ssafy.ios.lineup.backend.domain.entity.Recruit;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/* 계약 처리하는 Facade */
@Slf4j
@Service
@RequiredArgsConstructor
public class ContractFacadeImpl implements ContractFacade {

    private final ContractRequestService contractRequestService;
    private final TransferDetailService transferDetailService;
    private final NotificationService notificationService;
    private final ContractService contractService;
    private final UserCashService userCashService;
    private final RecruitService recruitService;
    private final UserValidator userValidator;
    private final UserService userService;

    /* 갑이 최종 계약 확정 */
    @Override
    @Transactional
    public void confirmContractByContractor(long contractRequestId, MultipartFile signature)
            throws CustomException {
        User loginUser = userService.selectLoginUser();
        ContractRequest contractRequest = contractRequestService.selectContractRequestById(
                contractRequestId);
        Recruit recruit = contractRequest.getApplication().getRecruit();

        userValidator.checkLoginUserNonNull(loginUser);
        userValidator.checkWriter(loginUser, recruit);
        // 계약 생성
        Contract contract = contractService.createContract(
                contractRequest.getApplication().getRecruit(),
                contractRequest.getApplication().getApplicant()
        );
        // 잔액 확인
//        if (recruit.getSuccessSalary() > userCashService.getCashByUser(loginUser)) {
//            throw new CustomException(LACK_OF_CASH);
//        }
        // 계약금 결제
        transferDetailService.createTransferDetail(
                loginUser,
                recruit.getSuccessSalary(),
                CASH_PAY
        );
        // 계약에 갑의 사인 저장
        contractService.saveContractorSignature(contract, signature);
        // 계약요청에 있는 을의 사인 계약에 저장
        contractService.saveContracteeSignature(contract, contractRequest);
        // 계약요청 삭제
        contractRequestService.deleteContractRequest(contractRequest);
        // TODO : 을에게 "계약 맺음" 알림 보내기
        notificationService.notifyContract(contract.getId());
    }

    /* 계약 취소 */
    @Override
    @Transactional
    public void cancelContract(Long contractId) throws CustomException {
        User loginUser = userService.selectLoginUser();
        userValidator.checkLoginUserNonNull(loginUser);

        Contract contract = contractService.selectContractById(contractId);
        User contractor = contract.getRecruit().getWriter();
        User contractee = contract.getContractee();
        // 환불 가능한 지 확인
        contractService.checkContractCreatedAfterOneHour(contract);
        recruitService.checkRecruitStarted(contract.getRecruit());
        // 이 계약
        if (!loginUser.equals(contractor) && !loginUser.equals(contractee)) {
            throw new IllegalArgumentException(NO_REQUIRED_USER_IN_CONTRACT.getMessage());
        }

        // 환불 가능 시간인지 확인
        checkRefundableContract(contract);

        // 갑이 결제한 금액 돌려주기
        transferDetailService.createTransferDetail(
                contractor,
                contract.getRecruit().getSuccessSalary(),
                CASH_REFUND);
        // 을이 결제한 금액 돌려주기
        transferDetailService.createTransferDetail(
                contractee,
                contract.getRecruit().getContracteeDeposit(),
                CASH_REFUND);
        // 갑의 잔액 동기화
        userCashService.updateCashByUserAndUpdatedCash(
                contractor,
                transferDetailService.calculateUserCash(loginUser));
        // 을의 잔액 동기화
        userCashService.updateCashByUserAndUpdatedCash(
                contractee,
                transferDetailService.calculateUserCash(loginUser));
        // 계약 삭제
        contractService.deleteContract(contract);

        if (loginUser.equals(contractor)) {
            // TODO : 을에게 "계약 취소" 알림 보내기
        } else if (loginUser.equals(contractee)) {
            // TODO : 갑에게 "계약 취소" 알림 보내기
        }
    }

    @Override
    public List<Contract> getContractListByFilter(ContractFilter contractFilter) {
        return contractService.selectContractByFilter(contractFilter);
    }

    @Override
    public void verifyWorkOut(String contractUuid) {

    }

    /* 환불 가능한 계약인지 확인 */
    private void checkRefundableContract(Contract contract) throws CustomException {
        recruitService.checkRecruitStarted(contract.getRecruit());
        contractService.checkContractCreatedAfterOneHour(contract);
    }
}
