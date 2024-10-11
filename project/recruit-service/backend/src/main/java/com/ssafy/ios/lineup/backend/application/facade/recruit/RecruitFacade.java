package com.ssafy.ios.lineup.backend.application.facade.recruit;

import com.ssafy.ios.lineup.backend.common.constant.RecruitStatus;
import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.domain.dto.application.ApplicantCardResponse;
import com.ssafy.ios.lineup.backend.domain.dto.chat.ChatRoomResponse;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitCardResponse;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitDetailResponse;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitFormRequest;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitSearchFilter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RecruitFacade {

    /* 공고 등록 */
    Long registerRecruit(RecruitFormRequest recruitFormRequest, MultipartFile recruitImg)
            throws CustomException;

    /* 공고 수정 */
    Long changedRecruit(Long recruitId, RecruitFormRequest recruitFormRequest,
                        MultipartFile recruitImg) throws CustomException;

    /* 공고 삭제 */
    Long deleteRecruit(Long recruitId) throws CustomException;

    /* 공고 상세 페이지 */
    RecruitDetailResponse selectRecruitDetail(Long recruitId) throws CustomException;

    /* 공고 지원자 리스트 조회 */
    List<ApplicantCardResponse> selectApplicantsToRecruit(Long recruitId) throws CustomException;

    /* 공고 채팅방 리스트 조회 */
    List<ChatRoomResponse> selectChatRoomsOfRecruit(Long recruitId);

    /* 자신이 작성한 공고에 대하여 상태를 변경 */
    Long changeRecruitStatus(Long recruitId, RecruitStatus recruitStatus) throws CustomException;

    /* 공고에 대한 좋아요 기능 */
    Long changeRecruitLikeStatus(Long recruitId) throws CustomException;

    /* 검색 결과에 따른 결과 조회 */
    List<RecruitCardResponse> getRecruitByFilter(RecruitSearchFilter recruitSearchFilter);

    /* 모든 공고 조회 */
    List<RecruitCardResponse> getRecruitsAll();
}
