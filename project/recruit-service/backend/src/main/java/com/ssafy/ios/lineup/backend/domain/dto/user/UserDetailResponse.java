package com.ssafy.ios.lineup.backend.domain.dto.user;

import lombok.Builder;
import lombok.Data;

/**
 * packageName    : com.ssafy.ios.lineup.backend.domain.dto.user fileName       : UserDetailResponse
 * author         : moongi date           : 9/23/24 description    :
 */
@Builder
@Data
public class UserDetailResponse {

    // TODO: 유저 정보 추가적으로 응답 필요
    private Long userId;
    private String email;
    private String profileImg;
    private String nickname;


}
