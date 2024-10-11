package com.ssafy.ios.lineup.backend.application.facade.application;

import com.ssafy.ios.lineup.backend.application.service.application.ApplicationService;
import com.ssafy.ios.lineup.backend.application.service.recruit.RecruitService;
import com.ssafy.ios.lineup.backend.application.service.user.UserService;
import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.common.validator.UserValidator;
import com.ssafy.ios.lineup.backend.domain.entity.Application;
import com.ssafy.ios.lineup.backend.domain.entity.ChatRoom;
import com.ssafy.ios.lineup.backend.domain.entity.Recruit;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ssafy.ios.lineup.backend.common.constant.error_code.UserErrorCode.NO_REQUIRED_USER_IN_CONTRACT;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationFacadeImpl implements ApplicationFacade {

    private final ApplicationService applicationService;
    private final RecruitService recruitService;
    private final UserValidator userValidator;
    private final UserService userService;


    @Override
    public Application registerApplication(Long recruitId) {
        User loginUser = userService.selectLoginUser();
        Recruit recruit = recruitService.selectRecruitByIdAndNull(recruitId);

        userValidator.checkLoginUserNonNull(loginUser);
        // todo : 공고 유효성 검사?

        Application application = Application.builder()
                .applicant(loginUser)  // loginUser를 applicant로 설정
                .recruit(recruit)
                .build();

        applicationService.createApplication(application);

        return application;
    }

    @Override
    @Transactional
    public Application cancelApplication(Long applicationId) {
        User loginUser = userService.selectLoginUser();
        userValidator.checkLoginUserNonNull(loginUser);

        Application application = applicationService.selectApplicationById(applicationId);
        // todo : 지원 유효성 검사?

        User writer = application.getRecruit().getWriter();
        User applicant = application.getApplicant();

        if (loginUser.equals(writer) || loginUser.equals(applicant)) {
            return applicationService.deleteApplication(application);
        } else {
            throw new CustomException(NO_REQUIRED_USER_IN_CONTRACT);
        }
    }

    @Override
    public Long setChatRoom(Application application, ChatRoom chatRoom) {
        Long chatRoomId = applicationService.setChatRoom(application, chatRoom);
        return chatRoomId;
    }

    @Override
    public Application searchApplication(Long recruitId) {
        User loginUser = userService.selectLoginUser();
        userValidator.checkLoginUserNonNull(loginUser);

        return applicationService.findApplication(recruitId, loginUser.getId());
    }
}
