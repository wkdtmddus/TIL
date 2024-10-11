package com.ssafy.ios.lineup.backend.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContractRequestStatus {
    WAITING("을의 계약 요청 수락 대기"), // 계약자가 계약요청을 보냈지만, 피계약자 계약요청 승인 전
    ACCEPT("을의 계약 요청 수락"); // 피계약자의 계약요청 수락 & 계약자 계약 전
//    @Deprecated
//    DENIED_BY_CONTRACTEE("을의 계약 요청 거절"), // 피계약자의 계약요청 거절
//    @Deprecated
//    CANCELED_BY_CONTRACTOR("갑의 계약 요청 취소"), // 계약자의 계약요청 거절
//    @Deprecated
//    SUCCESS("갑의 최종 계약 완료"); // 계약 완료

    private final String value;
}
