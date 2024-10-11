package com.ssafy.ios.lineup.backend.application.facade.contract;

import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.domain.dto.contract.ContractFilter;
import com.ssafy.ios.lineup.backend.domain.entity.Contract;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ContractFacade {
    // TODO : 로그인 유저가 갑(계약자)인 계약 정보 가져오기 기능 개발하기

    // TODO : 로그인 유저가 을(피계약자)인 계약 정보 가져오기 기능 개발하기

    /* 갑이 최종 계약 확정 */
    void confirmContractByContractor(long contractRequestId, MultipartFile signature)
            throws CustomException;

    /* 계약 취소 */
    void cancelContract(Long contractId) throws CustomException;

    List<Contract> getContractListByFilter(ContractFilter contractFilter);

    void verifyWorkOut(String contractUuid);
}
