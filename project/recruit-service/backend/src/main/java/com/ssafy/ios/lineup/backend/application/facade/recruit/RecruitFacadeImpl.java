package com.ssafy.ios.lineup.backend.application.facade.recruit;

import com.ssafy.ios.lineup.backend.application.service.application.ApplicationService;
import com.ssafy.ios.lineup.backend.application.service.chat.ChatMessageService;
import com.ssafy.ios.lineup.backend.application.service.chat.ChatRoomService;
import com.ssafy.ios.lineup.backend.application.service.recruit.RecruitService;
import com.ssafy.ios.lineup.backend.application.service.user.UserService;
import com.ssafy.ios.lineup.backend.common.constant.RecruitStatus;
import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.common.validator.UserValidator;
import com.ssafy.ios.lineup.backend.domain.dto.application.ApplicantCardResponse;
import com.ssafy.ios.lineup.backend.domain.dto.chat.ChatRoomResponse;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitCardResponse;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitDetailResponse;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitFormRequest;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitSearchFilter;
import com.ssafy.ios.lineup.backend.domain.entity.Recruit;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * packageName    : com.ssafy.ios.lineup.backend.application.facade.recruit fileName       :
 * RecruitFacadeImpl author         : moongi date           : 9/16/24 description    :
 */
@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class RecruitFacadeImpl implements RecruitFacade {

    private final UserService userService;
    private final UserValidator userValidator;
    private final RecruitService recruitService;
    private final ApplicationService applicationService;
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;

    /* 공고 등록 */
    public Long registerRecruit(RecruitFormRequest recruitFormRequest, MultipartFile recruitImg)
            throws CustomException {
        User loginUser = userService.selectLoginUser();
        System.out.println(loginUser);
        userValidator.checkLoginUserNonNull(loginUser);

        Recruit recruit = recruitService.createRecruit(recruitFormRequest, recruitImg);
        recruit.updateWriter(loginUser);

        return recruit.getId();
    }

    /* 공고 수정 */
    @Override
    public Long changedRecruit(Long recruitId, RecruitFormRequest recruitFormRequest,
                               MultipartFile recruitImg)
            throws CustomException {
        User loginUser = userService.selectLoginUser();
        Recruit recruit = recruitService.selectRecruitByIdAndNull(recruitId);

        userValidator.checkLoginUserNonNull(loginUser);
        userValidator.checkWriter(loginUser, recruit);

        recruitService.updateRecruit(recruitFormRequest, recruitId, recruitImg);

        return recruitId;
    }

    /* 공고 삭제 */
    @Override
    public Long deleteRecruit(Long recruitId) throws CustomException {
        User loginUser = userService.selectLoginUser();
        userValidator.checkLoginUserNonNull(loginUser);

        Recruit recruit = recruitService.selectRecruitByIdAndNull(recruitId);
        userValidator.checkWriter(loginUser, recruit);

        recruitService.deleteRecruit(recruitId);

        return recruitId;
    }

    /* 공고 상세 페이지 */
    @Override
    public RecruitDetailResponse selectRecruitDetail(Long recruitId) throws CustomException {
        User loginUser = userService.selectLoginUser();
        userValidator.checkUserNonNull(loginUser);

        Recruit recruit = recruitService.selectRecruitByIdAndNull(recruitId);
//        userValidator.checkWriter(loginUser, recruit);

        return recruitService.getRecruitDetail(recruitId);
    }

    /* 공고 지원자 리스트 조회 */
    @Override
    public List<ApplicantCardResponse> selectApplicantsToRecruit(Long recruitId)
            throws CustomException {
        User loginUser = userService.selectLoginUser();
        userValidator.checkUserNonNull(loginUser);

        // 자신이 작성한 공고의 지원자 리스트만 확인 가능
        Recruit recruit = recruitService.selectRecruitByIdAndNull(recruitId);
        userValidator.checkWriter(loginUser, recruit);

        return applicationService.getApplicantsByRecruitId(recruitId);
    }

    @Override
    public List<ChatRoomResponse> selectChatRoomsOfRecruit(Long recruitId) throws CustomException {
        User loginUser = userService.selectLoginUser();
        userValidator.checkUserNonNull(loginUser);

        // 자신이 작성한 공고의 채팅창 리스트만 확인 가능
        Recruit recruit = recruitService.selectRecruitByIdAndNull(recruitId);
        userValidator.checkWriter(loginUser, recruit);

        List<ChatRoomResponse> chatRooms = chatRoomService.getChatRoomsByRecruitId(recruitId);
        return chatMessageService.putRecentMessage(chatRooms);
    }

    /* 자신이 작성한 공고에 대하여 상태를 변경 */
    @Override
    public Long changeRecruitStatus(Long recruitId, RecruitStatus recruitStatus)
            throws CustomException {
        User loginUser = userService.selectLoginUser();
        userValidator.checkUserNonNull(loginUser);

        // 자신이 작성한 공고에 대해서만 상태가 변경 가능
        Recruit recruit = recruitService.selectRecruitByIdAndNull(recruitId);
        userValidator.checkWriter(loginUser, recruit);

        recruitService.updateRecruitStatus(recruitId, recruitStatus);

        return recruitId;
    }

    /* 공고에 대한 좋아요 기능 */
    @Override
    public Long changeRecruitLikeStatus(Long recruitId) throws CustomException {
        User loginUser = userService.selectLoginUser();
        userValidator.checkUserNonNull(loginUser);

        recruitService.toggleLikeRecruit(recruitId, loginUser);

        return recruitId;
    }

    /* 검색 결과에 따른 공고 조회 */
    @Override
    public List<RecruitCardResponse> getRecruitByFilter(RecruitSearchFilter recruitSearchFilter) {
        User loginUser = userService.selectLoginUser();
        userValidator.checkUserNonNull(loginUser);

        return recruitService.searchRecruitByFilter(recruitSearchFilter);
    }

    @Override
    public List<RecruitCardResponse> getRecruitsAll() {
        return recruitService.selectRecruits();
    }
}
