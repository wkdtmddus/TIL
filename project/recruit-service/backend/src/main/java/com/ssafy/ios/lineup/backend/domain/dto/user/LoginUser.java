package com.ssafy.ios.lineup.backend.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginUser {

    private String id;
    private String nickname;
    private String userImg;

}