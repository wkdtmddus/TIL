package com.ssafy.ios.lineup.backend.domain.entity;

import com.ssafy.ios.lineup.backend.common.constant.ContractStatus;
import jakarta.persistence.*;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Contract extends BaseEntity {

    @Builder
    Contract(Recruit recruit, User contractee) {
        this.recruit = recruit;
        this.contractee = contractee;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_id", nullable = false, unique = true)
    private Recruit recruit;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User contractee;

    @Column(length = 64)
    private String contractorSignatureFilename;

    @Column(length = 64)
    private String contracteeSignatureFilename;

    // TODO : Entity 생성 시 자동 생성되는지 확인
    @Column(length = 256)
    private String contractUuid = UUID.randomUUID().toString();

    @Enumerated(EnumType.STRING)
    private ContractStatus contractStatus = ContractStatus.WORK_START_BEFORE;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "work_start_verification_id")
//    private WorkStartVerification workStartVerification;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "verification_id")
//    private Verification verification;

    // 공고의 대행종류(service_type)에 따라 줄서기 or 구매 대행 테이블의 id 참조
    private Long workEndVerificationId;

    public void updateContractStatus(ContractStatus contractStatus) {
        this.contractStatus = contractStatus;
    }

    public void updateContractorSignatureFilename(String contractorSignatureFilename) {
        this.contractorSignatureFilename = contractorSignatureFilename;
    }

    public void updateContracteeSignatureFilename(String contracteeSignatureFilename) {
        this.contracteeSignatureFilename = contracteeSignatureFilename;
    }

}

