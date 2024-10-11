package com.ssafy.ios.lineup.backend.domain.dto.recruit;

import com.ssafy.ios.lineup.backend.domain.entity.embedded.Location;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.ssafy.ios.lineup.backend.domain.dto.recruit fileName       : RecruitFormDto
 * author         : moongi date           : 9/16/24 description    :
 */
@Getter
@NoArgsConstructor
public class RecruitFormRequest {

    private String title;
    private String content;
    private String placeType;
    private String serviceType;
    private String startDate;
    private String endDate;
    private String startAt;
    private String endAt;
    private Location location;
    private Integer contracteeDeposit;
    private Integer successSalary;
    private Integer failSalary;

    @Builder
    public RecruitFormRequest(String title, String content, String placeType,
            String serviceType, String startDate, String endDate, String startAt, String endAt,
//    public RecruitFormRequest(String title, String content, PlaceType placeType,
//            ServiceType serviceType, String startDate, String endDate, String startAt, String endAt,
            Location location,
            Integer contracteeDeposit, Integer successSalary, Integer failSalary) {
        this.title = title;
        this.content = content;
        this.placeType = placeType;
        this.serviceType = serviceType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startAt = startAt;
        this.endAt = endAt;
        this.location = location;
        this.contracteeDeposit = contracteeDeposit;
        this.successSalary = successSalary;
        this.failSalary = failSalary;
    }
}
