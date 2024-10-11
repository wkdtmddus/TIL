package com.ssafy.ios.lineup.backend.application.facade.contract_request;

import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.domain.dto.contract_request.ContractRequestResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface ContractRequestFacade {

    /* 갑이 을에게 계약요청 전송 */
    Long sendContractRequestByRecruitIdAndApplicantId(Long recruitId, Long applicantId)
            throws CustomException;

    /* 을이 갑의 계약요청 수락 */
    void confirmContractRequestByContractee(Long recruitId, MultipartFile signature)
            throws CustomException;

    /* 갑, 을의 계약요청 취소 */
    void cancelContractRequest(Long contractRequestId) throws CustomException;

    ContractRequestResponseDto searchContractRequestById(Long contractRequestId);
}
