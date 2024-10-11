package com.ssafy.ios.lineup.backend.domain.dto.user;

import com.ssafy.ios.lineup.backend.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * 모든 유저가 볼 수 있는 다른 유저 정보
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {

    private Long id;

    private String realName;

    @Builder
    public UserProfileResponse(User user) {
        this.id = user.getId();
        this.realName = user.getRealName();
    }
}
