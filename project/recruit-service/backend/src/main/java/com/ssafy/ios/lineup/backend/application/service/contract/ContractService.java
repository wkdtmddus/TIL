package com.ssafy.ios.lineup.backend.application.service.contract;

import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.domain.dto.contract.ContractFilter;
import com.ssafy.ios.lineup.backend.domain.entity.Contract;
import com.ssafy.ios.lineup.backend.domain.entity.ContractRequest;
import com.ssafy.ios.lineup.backend.domain.entity.Recruit;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ContractService {

    /* 계약 id(pk)로 계약 내역 가져오기 */
    Contract selectContractById(long contractId) throws CustomException;

//    Contract selectContractByUuid(String contractUuid) throws CustomException;

    /* 계약 요청으로 계약 가져오기 */
    Contract selectContractByContractRequest(ContractRequest contractRequest)
            throws CustomException;

    /* 공고와 지원자로 계약 생성 */
    Contract createContract(Recruit recruit, User contractee) throws CustomException;

    /* 계약 삭제하기 (실제 삭제 X , deletedAt만 update) */
    void deleteContract(Contract contract) throws CustomException;

    /* 계약이 생성된지 1시간이 지났는지 확인 */
    void checkContractCreatedAfterOneHour(Contract contract) throws CustomException;

    /* 계약에 갑의 사인 저장하기 */
    void saveContractorSignature(Contract contract, MultipartFile signature)
            throws CustomException;

    /* 계약요청에 있는 을의 사인 계약에 저장 */
    void saveContracteeSignature(Contract contract, ContractRequest contractRequest);

    List<Contract> selectContractByFilter(ContractFilter contractFilter);

    /* 계약서는 수정 불가 (삭제하고 다시 생성해야 함) */
}
