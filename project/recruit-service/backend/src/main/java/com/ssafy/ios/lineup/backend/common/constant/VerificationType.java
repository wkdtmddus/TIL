package com.ssafy.ios.lineup.backend.common.constant;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VerificationType {
    CHECK_IN("CHECK_IN", "출근 인증"),   // 출근 인증
    PURCHASE("PURCHASE", "구매 인증"),   // 구매 인증
    CHECK_OUT("CHECK_OUT", "퇴근 인증");   // 퇴근 인증

    private final String dbValue;
    private final String value;

    @Override
    public String toString() {
        return value;
    }

    public static VerificationType ofValue(String value) {
        return Arrays.stream(VerificationType.values())
                .filter(v -> v.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다: " + value));
    }

    public static VerificationType ofCode(String dbData) {
        return Arrays.stream(VerificationType.values())
                .filter(v -> v.getValue().equals(dbData))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
    }


}
