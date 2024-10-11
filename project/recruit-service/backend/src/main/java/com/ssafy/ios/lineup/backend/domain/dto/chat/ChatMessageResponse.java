package com.ssafy.ios.lineup.backend.domain.dto.chat;

import com.ssafy.ios.lineup.backend.common.constant.ContentType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ChatMessageResponse {

    private Long chatMessageId;
    private Long senderId;
    private Long chatRoomId;
    private ContentType contentType;
    private String content;
    private LocalDateTime createdAt;
    private Boolean isRead;

    @Builder
    public ChatMessageResponse(Long chatMessageId, Long senderId, Long chatRoomId, ContentType contentType,
                               String content, LocalDateTime createdAt, Boolean isRead) {
        this.chatMessageId = chatMessageId;
        this.senderId = senderId;
        this.chatRoomId = chatRoomId;
        this.contentType = contentType;
        this.content = content;
        this.createdAt = createdAt;
        this.isRead = isRead;
    }
}
