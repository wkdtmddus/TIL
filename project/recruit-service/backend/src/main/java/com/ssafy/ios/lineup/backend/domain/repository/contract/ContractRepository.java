package com.ssafy.ios.lineup.backend.domain.repository.contract;

import com.ssafy.ios.lineup.backend.domain.entity.Contract;
import com.ssafy.ios.lineup.backend.domain.entity.Recruit;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    Optional<Contract> findByIdAndDeletedAtNull(Long id);

    Optional<Contract> findByRecruitAndDeletedAtNull(Recruit recruit);
}
