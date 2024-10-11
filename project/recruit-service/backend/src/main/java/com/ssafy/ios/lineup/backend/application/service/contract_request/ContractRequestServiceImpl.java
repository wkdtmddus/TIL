package com.ssafy.ios.lineup.backend.application.service.contract_request;

import static com.ssafy.ios.lineup.backend.common.constant.error_code.ContractRequestErrorCode.ALREADY_EXIST_CONTRACT_REQUEST;
import static com.ssafy.ios.lineup.backend.common.constant.error_code.ContractRequestErrorCode.NO_CONTRACT_REQUEST;

import com.ssafy.ios.lineup.backend.common.constant.ContractRequestStatus;
import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.common.util.FileUtil;
import com.ssafy.ios.lineup.backend.domain.entity.Application;
import com.ssafy.ios.lineup.backend.domain.entity.ContractRequest;
import com.ssafy.ios.lineup.backend.domain.entity.Recruit;
import com.ssafy.ios.lineup.backend.domain.repository.contract_request.ContractRequestRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractRequestServiceImpl implements ContractRequestService {

    private final ContractRequestRepository contractRequestRepository;
    private final FileUtil fileUtil;

    /* 계약 요청 상태 "대기중"으로 변경 */
    @Override
    public void changeContractRequestStatusToWaiting(ContractRequest contractRequest)
            throws IllegalArgumentException {
        contractRequest.updateContractRequestStatus(ContractRequestStatus.WAITING);
        contractRequestRepository.save(contractRequest);
    }

    /* 계약 요청 상태 "을의 수락"로 변경 */
    @Override
    public void changeContractRequestStatusToAccept(ContractRequest contractRequest)
            throws CustomException {
        contractRequest.updateContractRequestStatus(ContractRequestStatus.ACCEPT);
        contractRequestRepository.save(contractRequest);
    }


    /* 계약 요청 생성하기 */
    @Override
    public ContractRequest createContractRequest(Application application)
            throws CustomException {
        ContractRequest contractRequest = ContractRequest.builder()
                .application(application)
                .build();
        return contractRequestRepository.save(contractRequest);
    }

    /* 계약 요청 id로 계약 요청 가져오기 */
    @Override
    public ContractRequest selectContractRequestById(long contractRequestId)
            throws CustomException {
        return contractRequestRepository.findById(contractRequestId)
                .orElseThrow(() -> new CustomException(NO_CONTRACT_REQUEST));
    }

    /* 을의 사인 저장하기 */
    @Override
    public void saveContracteeSignature(ContractRequest contractRequest, MultipartFile signature)
            throws CustomException {
        String uuid = UUID.randomUUID().toString();
        fileUtil.saveSignatureImg(signature, uuid); // 파일 저장
        contractRequest.updateContracteeSignatureFilename(
                uuid + "." + fileUtil.getExtension(signature)); // DB에 저장
        contractRequestRepository.save(contractRequest);
    }

    /* 계약 상태 가져오기 */
    @Override
    public ContractRequestStatus getStatus(ContractRequest contractRequest)
            throws CustomException {
        return contractRequest.getContractRequestStatus();
    }

    /* 해당 공고에 계약요청이 있는지 체크 */
    @Override
    public void checkRecruitHasContractRequest(Recruit recruit) throws CustomException {
        if (contractRequestRepository.isExistContractRequestByRecruit(recruit)) {
            throw new CustomException(ALREADY_EXIST_CONTRACT_REQUEST);
        }
    }

    /* 요청 삭제, 취소 */
    @Override
    public void deleteContractRequest(ContractRequest contractRequest) {
        contractRequestRepository.delete(contractRequest);
    }

    /* 지원 내역으로 계약요청 가져오기 */
    @Override
    public ContractRequest selectContractRequestByApplication(Application application) {
        return contractRequestRepository.findByApplication(application).orElseThrow();
    }
}
