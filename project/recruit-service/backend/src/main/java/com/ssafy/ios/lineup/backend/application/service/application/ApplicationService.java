package com.ssafy.ios.lineup.backend.application.service.application;

import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.domain.dto.application.ApplicantCardResponse;
import com.ssafy.ios.lineup.backend.domain.entity.Application;
import com.ssafy.ios.lineup.backend.domain.entity.ChatRoom;
import java.util.List;

public interface ApplicationService {


    Application selectApplicationById(long applicationId) throws CustomException;

    /* 특정 공고에 대한 지원자 리스트 조회 */
    List<ApplicantCardResponse> getApplicantsByRecruitId(Long recruitId) throws CustomException;

    /* 지원 생성 */
    Long createApplication(Application application) throws CustomException;

    /* 지원 삭제 */
    Application deleteApplication(Application application) throws CustomException;

    /* 생성된 채팅방 application 정보에 등록하기 */
    Long setChatRoom(Application application, ChatRoom chatRoom) throws CustomException;

    /* 유저가 지원한 내역이 있는지 확인 */
    Application findApplication(Long recruitId, Long applicantId);

}
