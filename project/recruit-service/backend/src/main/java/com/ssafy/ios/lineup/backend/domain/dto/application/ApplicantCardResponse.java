package com.ssafy.ios.lineup.backend.domain.dto.application;

import com.ssafy.ios.lineup.backend.common.util.FileUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ApplicantCardResponse {
    private Long applicantId;
    private String nickname;
    private String profileImgUrl;
    private LocalDateTime createdAt;
    private Long chatRoomId;

    @Builder
    public ApplicantCardResponse(Long applicantId, String nickname, String profileImg, LocalDateTime createdAt, Long chatRoomId, FileUtil fileUtil) {
        this.applicantId = applicantId;
        this.nickname = nickname;
        this.profileImgUrl = fileUtil.getUserProfileImg(profileImg);
        this.createdAt = createdAt;
        this.chatRoomId = chatRoomId;
    }
}
