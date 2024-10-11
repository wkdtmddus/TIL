package com.ssafy.ios.lineup.backend.domain.entity;

import com.ssafy.ios.lineup.backend.common.constant.PlaceType;
import com.ssafy.ios.lineup.backend.common.constant.RecruitStatus;
import com.ssafy.ios.lineup.backend.common.constant.ServiceType;
import com.ssafy.ios.lineup.backend.common.constant.converter.PlaceTypeConverter;
import com.ssafy.ios.lineup.backend.common.constant.converter.RecruitStatusConverter;
import com.ssafy.ios.lineup.backend.common.constant.converter.ServiceTypeConverter;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitFormRequest;
import com.ssafy.ios.lineup.backend.domain.entity.embedded.Location;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruit extends BaseEntity {

    @Id
    @Column(name = "recruit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User writer;

    @Column(length = 64)
    private String title;

    @Column(length = 1000)
    private String content;

    @Column(length = 64)
    private String recruitImg;

    @Convert(converter = PlaceTypeConverter.class)
    @Enumerated(EnumType.STRING)
    private PlaceType placeType;

    @Convert(converter = ServiceTypeConverter.class)
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @Convert(converter = RecruitStatusConverter.class)
    @Enumerated(EnumType.STRING)
    private RecruitStatus recruitStatus = RecruitStatus.RECRUITING;

    private Location location;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    private Integer contracteeDeposit;

    private Integer successSalary;

    private Integer failSalary;

    private Integer viewCount = 0;

    private Integer likeCount = 0;

    @Builder
    public Recruit(RecruitFormRequest recruitFormRequest, User writer) {
        this.writer = writer;
        this.title = recruitFormRequest.getTitle();
        this.content = recruitFormRequest.getContent();
        this.placeType = PlaceType.ofValue(recruitFormRequest.getPlaceType());
        this.serviceType = ServiceType.ofValue(recruitFormRequest.getServiceType());
        this.location = recruitFormRequest.getLocation();
        this.contracteeDeposit = recruitFormRequest.getContracteeDeposit();
        this.successSalary = recruitFormRequest.getSuccessSalary();
        this.failSalary = recruitFormRequest.getFailSalary();
    }

    // 공고 수정 메서드
    public void updateRecruit(RecruitFormRequest recruitFormRequest) {
        this.title = updateFieldIfNotNull(recruitFormRequest.getTitle(), this.title);
        this.content = updateFieldIfNotNull(recruitFormRequest.getContent(), this.content);
        this.placeType = updateFieldIfNotNull(PlaceType.ofValue(recruitFormRequest.getPlaceType()),
                this.placeType);
        this.serviceType = updateFieldIfNotNull(
                ServiceType.ofValue(recruitFormRequest.getServiceType()), this.serviceType);
        this.location = updateFieldIfNotNull(recruitFormRequest.getLocation(), this.location);
        this.contracteeDeposit = updateFieldIfNotNull(recruitFormRequest.getContracteeDeposit(),
                this.contracteeDeposit);
        this.successSalary = updateFieldIfNotNull(recruitFormRequest.getSuccessSalary(),
                this.successSalary);
        this.failSalary = updateFieldIfNotNull(recruitFormRequest.getFailSalary(), this.failSalary);
    }

    private <T> T updateFieldIfNotNull(T newValue, T currentValue) {
        return (newValue != null) ? newValue : currentValue;
    }

    /* 공고 상태 변경 */
    public void updateRecruitStatus(RecruitStatus recruitStatus) {
        this.recruitStatus = recruitStatus;
    }

    /* 공고 사진 파일 이름 변경 */
    public void updateRecruitImgFilename(String recruitImgFilename) {
        this.recruitImg = recruitImgFilename;
    }

    /* 공고 날짜 변경 */
    public void updateRecruitStartDate(LocalDateTime start_at) {
        this.startAt = start_at;
    }

    public void updateRecruitEndDate(LocalDateTime end_at) {
        this.endAt = end_at;
    }

    /* 공고 작성자 설정 */
    public void updateWriter(User writer) {
        this.writer = writer;
    }

    public void updateViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public void updateLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }
}

