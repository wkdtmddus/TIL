package com.ssafy.ios.lineup.backend.application.service.chat;

import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.domain.dto.chat.ChatRoomResponse;
import com.ssafy.ios.lineup.backend.domain.entity.Application;
import com.ssafy.ios.lineup.backend.domain.entity.ChatRoom;
import com.ssafy.ios.lineup.backend.domain.entity.User;

import java.util.List;

public interface ChatRoomService {

    /* 이용자 id로 채팅방 모아보기 */
    List<ChatRoomResponse> getChatRoomsByUser(User user) throws CustomException;

    /* 공고 id로 채팅방 모아보기 */
    List<ChatRoomResponse> getChatRoomsByRecruitId(Long recruitId) throws CustomException;

    /* 채팅방 id에 맞는 채팅방 갖고오기 */
    ChatRoom selectChatRoomById(Long chatRoomId) throws CustomException;

    /* 지원 id에 맞는 채팅방 갖고오기 */
    ChatRoom selectChatRoomByApplication(Application application) throws CustomException;

    /* 지원 id로 채팅방 생성하기 */
    ChatRoom createChatRoom(Application application) throws CustomException;

    /* 채팅방 인원들 id 갖고오기 */
    List<Long> getChatMemberIds(Long chatRoomId) throws CustomException;
}
