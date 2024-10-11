package com.ssafy.ios.lineup.backend.application.facade.user;

import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.domain.dto.user.UserProfileResponse;

public interface UserFacade {

    UserProfileResponse getUserProfileByUserId(Long userId) throws CustomException;
}
