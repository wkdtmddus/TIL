package com.ssafy.ios.lineup.backend.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RecruitSort {
    LATEST("최신 등록순"),
    DEADLINE_SOON("마감 임박순"),
    HIGHEST_SALARY("급여 높은 순"),
    MOST_VIEWED("조회수 높은 순"),
    MOST_LIKED("좋아요 높은 순");

    private final String value;
}
