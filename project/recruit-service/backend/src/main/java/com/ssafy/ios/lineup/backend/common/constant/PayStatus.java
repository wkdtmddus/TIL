package com.ssafy.ios.lineup.backend.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PayStatus {
    MONEY_IN("충전"),
    MONEY_OUT("환전"),
    CASH_PAY("계약금 결제"),
    CASH_REFUND("계약금 환급");

    private final String value;
}
