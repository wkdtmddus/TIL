package com.ssafy.ios.lineup.backend.domain.dto.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter // for Test Case
public class ChatMessageRequest {

    private Long chatMessageId;
    private Long chatRoomId;
    private Long senderId;
    private String content;
    // 프론트에서 이미지를 바이트 코드 혹은 Base64로 변환하여 서버로 전송
//    private String imgFile;

}
