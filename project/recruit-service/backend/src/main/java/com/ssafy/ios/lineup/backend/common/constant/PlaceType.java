package com.ssafy.ios.lineup.backend.common.constant;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PlaceType {
    POP_UP("POP_UP", "팝업스토어"),
    DEPARTMENT("DEPARTMENT", "백화점"),
    RESTAURANT("RESTAURANT", "음식점"),
    CAFE("CAFE", "카페"),
    ETC("ETC", "기타");

    private final String dbValue;
    private final String value;

    @Override
    public String toString() {
        return value;
    }

    public static PlaceType ofValue(String value) {
        return Arrays.stream(PlaceType.values())
                .filter(v -> v.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다: " + value));
    }

    public static PlaceType ofCode(String dbData) {
        return Arrays.stream(PlaceType.values())
                .filter(v -> v.getValue().equals(dbData))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
    }
}