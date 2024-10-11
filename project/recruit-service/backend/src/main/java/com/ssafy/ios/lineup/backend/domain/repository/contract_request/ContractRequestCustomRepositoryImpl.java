package com.ssafy.ios.lineup.backend.domain.repository.contract_request;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.ios.lineup.backend.domain.entity.QContractRequest;
import com.ssafy.ios.lineup.backend.domain.entity.Recruit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ContractRequestCustomRepositoryImpl implements ContractRequestCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean isExistContractRequestByRecruit(Recruit recruit) {
        QContractRequest contractRequest = QContractRequest.contractRequest;

        return queryFactory
                .selectOne()
                .from(contractRequest)
                .where(contractRequest.application.recruit.eq(recruit))
                .fetchFirst() != null;
    }
}
