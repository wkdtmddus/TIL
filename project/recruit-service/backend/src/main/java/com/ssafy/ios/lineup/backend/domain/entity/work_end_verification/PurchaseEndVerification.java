package com.ssafy.ios.lineup.backend.domain.entity.work_end_verification;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;

/* 구매대행 퇴근 인증 */
@Entity
@Getter
public class PurchaseEndVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_end_verification_id")
    private Long id;

    @Column(length = 64)
    private String workEndImgFilename;

    private LocalDateTime purchaseAt;

    private LocalDateTime exchangeAt;
}
