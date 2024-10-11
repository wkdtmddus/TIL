package com.ssafy.ios.lineup.backend.domain.dto.chat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class ChatRoomEnteringResponse {
    private Long chatRoomId;
    private Long recruitId;

    @Builder
    public ChatRoomEnteringResponse(Long chatRoomId, Long recruitId){
        this.chatRoomId = chatRoomId;
        this.recruitId = recruitId;
    }
}
