package com.ssafy.ios.lineup.backend.domain.dto.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ChatMessageReadRequest {

    private Long chatMessageId;
    private Long readerId;

}
