package com.ssafy.ios.lineup.backend.application.service.chat.redis;

import com.ssafy.ios.lineup.backend.domain.dto.chat.ChatMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisPublisher {

    private final RedisTemplate redisTemplate;

    public void publishMessage(ChatMessageResponse chatMessageResponse) {
        String topic = "chatRoom_" + chatMessageResponse.getChatRoomId();
        redisTemplate.convertAndSend(topic, chatMessageResponse);
    }

    /* 지금 방법 안되면 나중에 쓸 용도 */
//    public void publishChatRoomList(ChatMessageRequest chatMessageRequest) {
//        String topic = "chatMember_" + chatMessageRequest.getChatRoomId();
//        redisTemplate.convertAndSend(topic, chatMessageRequest);
//    }
}
