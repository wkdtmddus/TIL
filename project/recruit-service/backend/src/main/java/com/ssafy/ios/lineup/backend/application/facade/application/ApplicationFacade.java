package com.ssafy.ios.lineup.backend.application.facade.application;

import com.ssafy.ios.lineup.backend.domain.entity.Application;
import com.ssafy.ios.lineup.backend.domain.entity.ChatRoom;

public interface ApplicationFacade {

    /* 지원 등록 : 지원 객체 반환 */
    Application registerApplication(Long recruitId);

    /* 지원 취소 */
    Application cancelApplication(Long applicationId);

    /* Application에 채팅방 등록 */
    Long setChatRoom(Application application, ChatRoom chatRoom);

    /* 지원 정보가 존재하는지 여부 */
    Application searchApplication(Long recruitId);
}
