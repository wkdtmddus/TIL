package com.ssafy.ios.lineup.backend.application.service.contract_request;

import com.ssafy.ios.lineup.backend.common.constant.ContractRequestStatus;
import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.domain.entity.Application;
import com.ssafy.ios.lineup.backend.domain.entity.ContractRequest;
import com.ssafy.ios.lineup.backend.domain.entity.Recruit;
import org.springframework.web.multipart.MultipartFile;

public interface ContractRequestService {

    /* 계약 요청의 상태를 대기중으로 변경 */
    void changeContractRequestStatusToWaiting(ContractRequest contractRequest)
            throws CustomException;

    /* 계약 요청의 상태를 승인으로 변경 */
    void changeContractRequestStatusToAccept(ContractRequest contractRequest)
            throws CustomException;


    /* 계약 요청 생성 */
    ContractRequest createContractRequest(Application application) throws CustomException;

    /* 계약 요청 id로 계약 요청 가져오기 */
    ContractRequest selectContractRequestById(long contractRequestId)
            throws CustomException;

    /* 을이 계약서에 서명하기 */
    void saveContracteeSignature(ContractRequest contractRequest, MultipartFile signature)
            throws CustomException;

    ContractRequestStatus getStatus(ContractRequest contractRequest)
            throws CustomException;

    void checkRecruitHasContractRequest(Recruit recruit) throws CustomException;

    void deleteContractRequest(ContractRequest contractRequest);

    ContractRequest selectContractRequestByApplication(Application application);
}
