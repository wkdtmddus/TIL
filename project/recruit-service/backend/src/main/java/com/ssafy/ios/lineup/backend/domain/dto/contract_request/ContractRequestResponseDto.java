package com.ssafy.ios.lineup.backend.domain.dto.contract_request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ContractRequestResponseDto {

    private String recruitTitle;
    private String recruitContent;
    private String recruitImgUrl;

    private String streetAddress;
    private String district;
    private Float latitude; // 위도
    private Float longitude; // 경도

    private String contractorNickname;
    private String contracteeNickname;
    private String contractorSignatureImgUrl;
    private String contracteeSignatureImgUrl;

}
