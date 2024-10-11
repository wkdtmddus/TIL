package com.ssafy.ios.lineup.backend.domain.repository.chat;

import com.ssafy.ios.lineup.backend.domain.entity.ChatMessage;
import java.util.List;

public interface ChatMessageCustomRepository {

    List<ChatMessage> findAllByChatRoomIdOrderByCreatedAtDesc(Long chatRoomId);

    ChatMessage findTopByChatRoomIdOrderByCreatedAtDesc(Long chatRoomId);
}
