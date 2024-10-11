package com.ssafy.ios.lineup.backend.domain.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.ssafy.ios.lineup.backend.domain.entity.embedded fileName       : Address
 * author         : moongi date           : 9/16/24 description    :
 */
@Getter
@NoArgsConstructor
@Embeddable
public class Location {

    @Column(length = 64)
    private String streetAddress;
    private String district;
    private Float latitude; // 위도
    private Float longitude; //

    @Builder
    public Location(String streetAddress, String district, Float latitude, Float longitude) {
        this.streetAddress = streetAddress;
        this.district = district;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void updateDistrict(String district) {
        this.district = district;
    }
}
