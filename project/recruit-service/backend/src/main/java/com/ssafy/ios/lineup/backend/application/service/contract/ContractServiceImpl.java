package com.ssafy.ios.lineup.backend.application.service.contract;

import static com.ssafy.ios.lineup.backend.common.constant.error_code.ContractErrorCode.NO_CONTRACT;
import static com.ssafy.ios.lineup.backend.common.constant.error_code.ContractErrorCode.ONE_HOUR_EXCEEDED_CONTRACT_CREATION_TIME;

import com.ssafy.ios.lineup.backend.common.constant.RecruitStatus;
import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.common.util.FileUtil;
import com.ssafy.ios.lineup.backend.domain.dto.contract.ContractFilter;
import com.ssafy.ios.lineup.backend.domain.entity.Contract;
import com.ssafy.ios.lineup.backend.domain.entity.ContractRequest;
import com.ssafy.ios.lineup.backend.domain.entity.Recruit;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import com.ssafy.ios.lineup.backend.domain.repository.contract.ContractRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final FileUtil fileUtil;

    /* 계약 id로 계약 가져오기 */
    @Override
    public Contract selectContractById(long contractId) throws CustomException {
        return contractRepository.findById(contractId)
                .orElseThrow(() -> new CustomException(NO_CONTRACT));
    }

    /* 유효한 계약만 가져오기 (하나만 가져와져야 함) */
    @Override
    public Contract selectContractByContractRequest(ContractRequest contractRequest)
            throws CustomException {
        Recruit recruit = contractRequest.getApplication().getRecruit();
        return contractRepository.findByRecruitAndDeletedAtNull(recruit)
                .orElseThrow(() -> new CustomException(NO_CONTRACT));
    }

    /* 계약 생성 */
    @Override
    public Contract createContract(Recruit recruit, User contractee)
            throws CustomException {
        /* 구인 완료로 상태 변경 */
        recruit.updateRecruitStatus(RecruitStatus.COMPLETED);

        Contract contract = Contract.builder()
                .recruit(recruit)
                .contractee(contractee)
                .build();
        return contractRepository.save(contract);
    }

    /* 계약 취소, 삭제 */
    @Override
    public void deleteContract(Contract contract) throws IllegalArgumentException {
        /* 갑과 을이 계약을 취소했을 경우 공고의 상태를 다시 구인 중으로 변경 */
        contract.getRecruit().updateRecruitStatus(RecruitStatus.RECRUITING);
        contractRepository.delete(contract);
    }

    /* 계약이 생성된지 1시간이 지났는지 확인 */
    @Override
    public void checkContractCreatedAfterOneHour(Contract contract) throws CustomException {
        // 현재 시각이 계약 생성 시간 1시간 이후이면
        if (LocalDateTime.now().minusHours(1).isAfter(contract.getCreatedAt())) {
            throw new CustomException(ONE_HOUR_EXCEEDED_CONTRACT_CREATION_TIME);
        }
    }

    /* 계약에 갑의 사인 저장 */
    @Override
    public void saveContractorSignature(Contract contract, MultipartFile signature)
            throws CustomException {
        String uuid = UUID.randomUUID().toString();
        fileUtil.saveSignatureImg(signature, uuid); // 파일 저장
        contract.updateContractorSignatureFilename(
                uuid + "." + fileUtil.getExtension(signature)); // DB에 저장
        contractRepository.save(contract);
    }

    /* 계약요청에 있는 을의 사인 계약에 저장 */
    @Override
    public void saveContracteeSignature(Contract contract, ContractRequest contractRequest) {
        contract.updateContracteeSignatureFilename(
                contractRequest.getContracteeSignatureFilename());
    }

    @Override
    public List<Contract> selectContractByFilter(ContractFilter contractFilter) {
        // TODO : 변경해야 함
        return contractRepository.findAll();
    }

    /* 갑의 계약 실패 */

    /* 을의 계약 실패 */

    /* 계약 성공 시 */

}
