package com.ssafy.ios.lineup.backend.application.service.chat.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.ios.lineup.backend.domain.dto.chat.ChatMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RedisSubscriber {

    private final ObjectMapper objectMapper;
    private final SimpMessageSendingOperations messagingTemplate;

    public void sendMessage(String publishMessage) {
        try {
            ChatMessageRequest chatMessage =
                    objectMapper.readValue(publishMessage, ChatMessageRequest.class);

            messagingTemplate.convertAndSend("/sub/chat/room/" + chatMessage.getChatRoomId(),
                    chatMessage);
        } catch (Exception e) {
            System.out.println("Subscriber Error");
        }
    }

    /* update 해야하는 인원들에 갱신 알람 보내기 */
    public void updateRoomList(List<Long> chatMembers) {
        for (Long chatMemberId : chatMembers) {
            messagingTemplate.convertAndSend("/sub/chat/rooms/update/" + chatMemberId, "update");
        }
    }
}
