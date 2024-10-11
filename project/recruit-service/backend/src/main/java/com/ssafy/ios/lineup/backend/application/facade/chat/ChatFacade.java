package com.ssafy.ios.lineup.backend.application.facade.chat;

import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.domain.dto.chat.ChatMessageRequest;
import com.ssafy.ios.lineup.backend.domain.dto.chat.ChatMessageResponse;
import com.ssafy.ios.lineup.backend.domain.dto.chat.ChatRoomResponse;
import com.ssafy.ios.lineup.backend.domain.dto.user.CustomUserDetails;
import com.ssafy.ios.lineup.backend.domain.entity.Application;
import com.ssafy.ios.lineup.backend.domain.entity.ChatRoom;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ChatFacade {

    /* 텍스트 메세지 보내기 */
    void sendTextMessage(long chatRoomId, String text)
            throws CustomException;

    /* 파일 첨부 메세지 보내기 */
    void sendFileMessage(long chatRoomId, MultipartFile file)
            throws CustomException;

    /* 채팅방 나가기 */
    void exitChatRoom(Long chatRoomId) throws CustomException;

    /* 지원취소 시 자동으로 나가지기 */
    void exitChatRoomByApplication(Application application) throws CustomException;

    /* 지원 시 자동으로 채팅방 생성하기 */
    ChatRoom createChat(Application application) throws CustomException;

    /* 채팅방 입장 시 메세지들 불러오기 */
    List<ChatMessageResponse> getChatMessages(Long chatRoomId) throws CustomException;

    /* 메세지 저장 */
    ChatMessageResponse saveMessage(ChatMessageRequest chatMessageRequest) throws CustomException;

    /* 메세지 Publish하기 */
    void sendChatMessage(ChatMessageRequest chatMessageRequest) throws CustomException;

    /* 채팅방 목록 보기 */
    List<ChatRoomResponse> getChatRooms() throws CustomException;

    /* 메세지 읽음 처리 */
    void markMessageRead(Long messageId, Long readerId) throws CustomException;

    /* 채팅방 Id 찾기 : 지원자용 */
    Long getChatRoomId(Application application);

    /* 보낸 이가 지원자인지 확인용 */
    Boolean isApplicant(Long chatRoomId);
}
