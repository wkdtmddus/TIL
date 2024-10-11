package com.ssafy.ios.lineup.backend.application.controller.contract;

import com.ssafy.ios.lineup.backend.application.facade.Verification.VerificationFacade;
import com.ssafy.ios.lineup.backend.application.facade.contract.ContractFacade;
import com.ssafy.ios.lineup.backend.domain.dto.contract.ContractFilter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/* 계약 처리하는 컨트롤러 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class ContractController {

    private final ContractFacade contractFacade;
    private final VerificationFacade verificationFacade;

    /* 갑이 계약 (확정)하기 */
    @PostMapping(path = "/contracts", consumes = {
            MediaType.APPLICATION_JSON_VALUE, "multipart/form-data"})
    public ResponseEntity<?> createContract(
            @RequestParam Long contractRequestId,
            @RequestPart(value = "signature", required = false) MultipartFile signatureImg) {

        contractFacade.confirmContractByContractor(contractRequestId, signatureImg);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* 갑이 계약 취소 */
    @DeleteMapping("/contracts/{contract-id}")
    public ResponseEntity<?> cancelContract(@PathVariable("contract-id") Long contractId) {

        contractFacade.cancelContract(contractId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* 계약 리스트 가져오기 */
    @GetMapping("/contracts")
    public ResponseEntity<?> getContractList(@ModelAttribute @Valid ContractFilter contractFilter) {
        return new ResponseEntity<>(
                contractFacade.getContractListByFilter(contractFilter),
                HttpStatus.OK);
    }


    @PostMapping("/contracts/verifications/checkout/{contract-uuid}")
    public ResponseEntity<?> verifyCheckOut(@PathVariable("contract-id") Long contractId) {
//        Contract contract = verificationFacade.verifyCheckOut(contractId);
//        contractFacade.complete(contract);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
