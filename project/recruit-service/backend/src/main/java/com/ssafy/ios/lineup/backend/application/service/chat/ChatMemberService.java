package com.ssafy.ios.lineup.backend.application.service.chat;

import com.ssafy.ios.lineup.backend.domain.entity.Application;
import com.ssafy.ios.lineup.backend.domain.entity.ChatMember;
import com.ssafy.ios.lineup.backend.domain.entity.ChatRoom;
import com.ssafy.ios.lineup.backend.domain.entity.User;

public interface ChatMemberService {

    /* 특정 채팅방의 ChatMember 정보 갖고오기 */
    ChatMember selectChatMemberByChatRoomAndUser(ChatRoom chatRoom, User user);

    /* 채팅방 나감 처리 */
    void exitChatRoom(ChatMember chatMember);

    /* 채팅방 멤버 정보 생성 */
    void createChatMember(ChatRoom chatRoom, Application application);
}
