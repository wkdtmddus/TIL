package com.ssafy.ios.lineup.backend.domain.repository.chat;

import com.ssafy.ios.lineup.backend.domain.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>,
        ChatMessageCustomRepository {

}
