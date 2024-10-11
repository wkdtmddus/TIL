package com.ssafy.ios.lineup.backend.domain.dto.chat;

import com.ssafy.ios.lineup.backend.common.util.FileUtil;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomResponse {

    private Long chatRoomId;
    private String nickname;
    private String profileImgUrl;
    private String recentMessage;
    private LocalDateTime createdAt;
    private Boolean isRead;
    private Long recruitId;

    @Builder
    public ChatRoomResponse(Long chatRoomId, User opponent, Long recruitId, FileUtil fileUtil) {
        this.chatRoomId = chatRoomId;
        this.nickname = opponent.getNickname();
        this.profileImgUrl = fileUtil.getUserProfileImg(opponent.getProfileImgFilename());
        this.recruitId = recruitId;
    }
}
