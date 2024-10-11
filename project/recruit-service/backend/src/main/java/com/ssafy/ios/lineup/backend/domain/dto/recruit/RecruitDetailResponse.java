package com.ssafy.ios.lineup.backend.domain.dto.recruit;

import com.ssafy.ios.lineup.backend.common.constant.PlaceType;
import com.ssafy.ios.lineup.backend.common.constant.RecruitStatus;
import com.ssafy.ios.lineup.backend.common.constant.ServiceType;
import com.ssafy.ios.lineup.backend.common.util.DateTimeUtil;
import com.ssafy.ios.lineup.backend.domain.entity.embedded.Location;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * packageName    : com.ssafy.ios.lineup.backend.domain.dto.recruit fileName       :
 * RecruitDetailResponse author         : moongi date           : 9/16/24 description    :
 */
@Getter
@NoArgsConstructor
public class RecruitDetailResponse {

    private Long id;
    private Long writerId;
    private String writerNickname;
    private String writerProfileImgUrl;
    private String title;
    private String content;
    private String recruitImgUrl;
    private String placeType;
    private String recruitStatus;
    private String serviceType;
    private String startDate;
    private String startAt;
    private String endDate;
    private String endAt;
    private Location location;
    private Integer successSalary;
    private Integer failSalary;
    private Integer contracteeDeposit;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public RecruitDetailResponse(Long id, Long writerId, String writerNickname,
                                 String writerProfileImg, String title,
                                 String content,
                                 String recruitImg,
                                 PlaceType placeType, RecruitStatus recruitStatus,
                                 ServiceType serviceType,
                                 LocalDateTime startAt, LocalDateTime endAt, Location location,
                                 Integer successSalary, Integer failSalary, Integer contracteeDeposit,
                                 LocalDateTime createdAt,
                                 LocalDateTime modifiedAt) {
        String[] startDateTime = DateTimeUtil.splitDateTime(startAt);
        String[] endDateTime = DateTimeUtil.splitDateTime(endAt);
        this.id = id;
        this.writerId = writerId;
        this.writerNickname = writerNickname;
        this.writerProfileImgUrl = writerProfileImg;
        this.title = title;
        this.content = content;
        this.recruitImgUrl = recruitImg;
        this.placeType = (placeType != null) ? placeType.getValue() : null;
        this.recruitStatus = (recruitStatus != null) ? recruitStatus.getValue() : null;
        this.serviceType = (serviceType != null) ? serviceType.getValue() : null;
        this.startDate = startDateTime[0];
        this.startAt = startDateTime[1];
        this.endDate = endDateTime[0];
        this.endAt = endDateTime[1];
        this.location = location;
        this.successSalary = successSalary;
        this.failSalary = failSalary;
        this.contracteeDeposit = contracteeDeposit;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

}
