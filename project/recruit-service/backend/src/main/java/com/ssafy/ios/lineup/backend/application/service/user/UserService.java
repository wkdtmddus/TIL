package com.ssafy.ios.lineup.backend.application.service.user;

import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.domain.dto.user.UserProfileResponse;
import com.ssafy.ios.lineup.backend.domain.entity.User;

public interface UserService {

    User selectLoginUser() throws CustomException;

    User selectUserById(Long id) throws CustomException;

    UserProfileResponse searchUserProfileById(Long id) throws CustomException;
}
