package com.ssafy.ios.lineup.backend.domain.dto.recruit;

import com.ssafy.ios.lineup.backend.common.constant.RecruitStatus;
import com.ssafy.ios.lineup.backend.common.util.DateTimeUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * packageName    : com.ssafy.ios.lineup.backend.domain.dto.recruit fileName       :
 * RecruitCardResponse author         : moongi date           : 9/23/24 description    :
 */
@Getter
@NoArgsConstructor
public class RecruitCardResponse {

    private Long id;
    private String title;
    private String recruitImgUrl;
    //    private String serviceType;
    private Integer successSalary;
    private String recruitStatus;
    private String startDate;
    private String startAt;
    private String endDate;
    private String endAt;
    private Integer viewCount;
    private Integer likeCount;
    private String streetAddress;

    @Builder
    public RecruitCardResponse(Long id, String title, String recruitImg, Integer successSalary,
                               RecruitStatus recruitStatus, LocalDateTime startAt, LocalDateTime endAt,
                               Integer viewCount, Integer likeCount, String streetAddress) {
        String[] startDateTime = DateTimeUtil.splitDateTime(startAt);
        String[] endDateTime = DateTimeUtil.splitDateTime(endAt);
        String ss = DateTimeUtil.formatDate(startAt);
        this.id = id;
        this.title = title;
        this.recruitImgUrl = recruitImg;
        this.successSalary = successSalary;
        this.recruitStatus = (recruitStatus != null) ? recruitStatus.getValue() : null;
//        this.startDate = startDateTime[0];
        this.startDate = ss;
        this.startAt = startDateTime[1];
        this.endDate = endDateTime[0];
        this.endAt = endDateTime[1];
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.streetAddress = streetAddress;
    }
}
