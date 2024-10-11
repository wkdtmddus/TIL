package com.ssafy.ios.lineup.backend.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * packageName    : com.ssafy.ios.lineup.backend.common.constant
 * fileName       : RecruitStatus
 * author         : moongi
 * date           : 9/16/24
 * description    :
 */
@AllArgsConstructor
@Getter
public enum RecruitStatus {
    RECRUITING("RECRUITING", "구인 중"),
    COMPLETED("COMPLETED", "구인 완료"),
    EXPIRED("EXPIRED", "기간 만료");

    private final String dbValue;
    private final String value;

    @Override
    public String toString() {
        return value;
    }

    public static RecruitStatus ofValue(String value) {
        return Arrays.stream(RecruitStatus.values())
                .filter(v -> v.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다: " + value));
    }
    public static RecruitStatus ofCode(String dbData) {
        return Arrays.stream(RecruitStatus.values())
                .filter(v -> v.getValue().equals(dbData))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
    }
}
