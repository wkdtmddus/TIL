package com.ssafy.ios.lineup.backend.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContractStatus {
    WORK_START_BEFORE("대행 시작 전"),
    WORK_START("대행 시작"),
    WORK_START_VERIFICATION_SUCCESS("출근 인증 완료"),
    WORK_END_QUEUE_VERIFICATION_SUCCESS("줄서기 대행 인증 완료"),
    WORK_END_PURCHASE_VERIFICATION_SUCCESS("구매 대행 인증 완료"),
    WORK_END("대행 끝");

    private final String value;
}
