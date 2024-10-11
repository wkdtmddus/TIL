package com.ssafy.ios.lineup.backend.domain.entity.work_end_verification;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;

/* 줄서기대행 퇴근 인증 */
@Entity
@Getter
public class QueueEndVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "queue_end_verification_id")
    private Long id;

    @Column
    private LocalDateTime queueEndAt;
}
