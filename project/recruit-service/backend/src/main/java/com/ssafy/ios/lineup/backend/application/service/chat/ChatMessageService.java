package com.ssafy.ios.lineup.backend.application.service.chat;

import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.domain.dto.chat.ChatMessageResponse;
import com.ssafy.ios.lineup.backend.domain.dto.chat.ChatRoomResponse;
import com.ssafy.ios.lineup.backend.domain.entity.ChatMember;
import com.ssafy.ios.lineup.backend.domain.entity.ChatMessage;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ChatMessageService {

    /* 텍스트 메세지 저장 */
    ChatMessageResponse createTextMessage(ChatMember chatMember, String text) throws CustomException;

    /* 파일첨부 메세지 저장 */
    void createFileMessage(ChatMember chatMember, MultipartFile file) throws CustomException;

    /* ChatRoomResponse에 최근 메세지 값 넣기 */
    List<ChatRoomResponse> putRecentMessage(List<ChatRoomResponse> chatRooms)
            throws CustomException;

    /* 채팅방 id로 최근 메세지 반환 */
    ChatMessage getRecentMessageByChatRoomId(Long chatRoomId) throws CustomException;

    /* 채팅방의 모든 메세지 반환 */
    List<ChatMessageResponse> getAllMessages(Long chatRoomId, User user) throws CustomException;

    void markMessageRead(Long messageId, Long readerId) throws CustomException;
}
