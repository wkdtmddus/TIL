package com.ssafy.ios.lineup.backend.application.controller.contract_request;

import com.ssafy.ios.lineup.backend.application.facade.contract_request.ContractRequestFacade;
import com.ssafy.ios.lineup.backend.application.facade.pay.PayFacade;
import com.ssafy.ios.lineup.backend.domain.dto.contract.RealContractDto;
import com.ssafy.ios.lineup.backend.domain.repository.contract_request.ContractRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/* 계약 요청 처리하는 컨트롤러 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class ContractRequestController {

    private final ContractRequestFacade contractRequestFacade;
    private final PayFacade payFacade;
    private final ContractRequestRepository contractRequestRepository;

    /* 갑이 을에게 계약 요청 보내기 */
    @PostMapping("/contract-requests/send")
    public ResponseEntity<?> sendContractRequest(
            @RequestBody RealContractDto realContractDto) {
        return new ResponseEntity<>(
                contractRequestFacade.sendContractRequestByRecruitIdAndApplicantId(
                        realContractDto.getRecruitId(),
                        realContractDto.getApplicantId()),
                HttpStatus.OK);
    }

    /* 을이 계약요청 수락 */
    @Transactional
    @PostMapping(path = "/contract-requests/accept",
            consumes = {MediaType.APPLICATION_JSON_VALUE, "multipart/form-data"})
    public ResponseEntity<?> acceptContractRequest(
            @RequestParam Long recruitId,
            @RequestPart(value = "signature", required = false) MultipartFile signatureImg) {

        contractRequestFacade
                .confirmContractRequestByContractee(recruitId, signatureImg);
        payFacade.syncCash(); // TODO : AOP 로 빼기
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* 갑이 계약 요청 취소 or 을이 계약 요청 거절 */
    @Transactional
    @DeleteMapping(path = "/contract-requests/{contract-request-id}")
    public ResponseEntity<?> cancelContractRequest(
            @PathVariable("contract-request-id") Long contractRequestId) {

        contractRequestFacade.cancelContractRequest(contractRequestId);
        payFacade.syncCash(); // TODO : AOP 로 빼기
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
     * 계약 정보 가져오기
     * */
    @GetMapping("/contract-requests/{contract-request-id}")
    public ResponseEntity<?> getContractRequest(
            @PathVariable("contract-request-id") Long contractRequestId) {
        return new ResponseEntity<>(
                contractRequestFacade.searchContractRequestById(contractRequestId), HttpStatus.OK);
    }
}
