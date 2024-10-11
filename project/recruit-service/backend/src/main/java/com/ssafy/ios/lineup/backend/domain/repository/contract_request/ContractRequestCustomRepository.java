package com.ssafy.ios.lineup.backend.domain.repository.contract_request;

import com.ssafy.ios.lineup.backend.domain.entity.Recruit;

public interface ContractRequestCustomRepository {

    boolean isExistContractRequestByRecruit(Recruit recruit);
}
