package com.ssafy.ios.lineup.backend.domain.repository.contract_request;

import com.ssafy.ios.lineup.backend.domain.entity.Application;
import com.ssafy.ios.lineup.backend.domain.entity.ContractRequest;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRequestRepository
        extends JpaRepository<ContractRequest, Long>, ContractRequestCustomRepository {

    Optional<ContractRequest> findByApplication(Application application);
}
