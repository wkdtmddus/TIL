package com.ssafy.ios.lineup.backend.domain.repository.verification;

import com.ssafy.ios.lineup.backend.common.constant.ServiceType;
import com.ssafy.ios.lineup.backend.common.constant.VerificationType;
import com.ssafy.ios.lineup.backend.domain.entity.Contract;
import com.ssafy.ios.lineup.backend.domain.entity.Recruit;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import com.ssafy.ios.lineup.backend.domain.entity.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRepository extends JpaRepository<Verification, Long> {
    Verification findByContractAndVerificationType(Contract contract, VerificationType verificationType);
}
