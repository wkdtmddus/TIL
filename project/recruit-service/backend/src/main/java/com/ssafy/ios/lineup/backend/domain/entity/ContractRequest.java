package com.ssafy.ios.lineup.backend.domain.entity;

import com.ssafy.ios.lineup.backend.common.constant.ContractRequestStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ContractRequest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_request_id")
    private Long id;

    // 한 공고에 계약 요청은 하나씩만 가능
//    @ManyToOne(fetch = FetchType.LAZY)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    @Column(length = 64)
    private String contracteeSignatureFilename;

    // 계약 요청 생성 시 "대기중" 으로 초기화
    @Enumerated(EnumType.STRING)
    private ContractRequestStatus contractRequestStatus = ContractRequestStatus.WAITING;

    public void updateContracteeSignatureFilename(String contracteeSignatureFilename) {
        this.contracteeSignatureFilename = contracteeSignatureFilename;
    }

    public void updateContractRequestStatus(ContractRequestStatus contractRequestStatus) {
        this.contractRequestStatus = contractRequestStatus;
    }

    @Builder
    public ContractRequest(Application application) {
        this.application = application;
    }
}

