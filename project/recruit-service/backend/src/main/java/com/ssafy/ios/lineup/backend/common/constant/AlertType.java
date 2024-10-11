package com.ssafy.ios.lineup.backend.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AlertType {
    CONTRACT_REQUEST("계약 요청", ""),
    CONTRACT_REQUEST_ACCEPT("계약 요청 승인", ""),
    CONTRACT_REQUEST_REJECT("계약 요청 거절", ""),
    CONTRACT("계약", "");

    private final String title;
    private final String message;
}
