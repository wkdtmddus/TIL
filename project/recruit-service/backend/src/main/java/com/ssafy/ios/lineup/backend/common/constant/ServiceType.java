package com.ssafy.ios.lineup.backend.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum ServiceType {
    QUEUE("QUEUE", "줄서기 대행"),
    PURCHASE("PURCHASE", "구매 대행");

    private final String dbValue;
    private final String value;

    @Override
    public String toString() {
        return value;
    }

    public static ServiceType ofValue(String value) {
        return Arrays.stream(ServiceType.values())
                .filter(v -> v.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다: " + value));
    }
    public static ServiceType ofCode(String dbData) {
        return Arrays.stream(ServiceType.values())
                .filter(v -> v.getValue().equals(dbData))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
    }
}
