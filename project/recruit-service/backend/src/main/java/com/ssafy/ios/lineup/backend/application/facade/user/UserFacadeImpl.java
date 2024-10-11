package com.ssafy.ios.lineup.backend.application.facade.user;

import com.ssafy.ios.lineup.backend.application.service.user.UserService;
import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.domain.dto.user.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    @Override
    public UserProfileResponse getUserProfileByUserId(Long userId) throws CustomException {
        return userService.searchUserProfileById(userId);
    }
}
