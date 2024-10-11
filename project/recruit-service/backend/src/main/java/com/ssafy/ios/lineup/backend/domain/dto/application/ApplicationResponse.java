package com.ssafy.ios.lineup.backend.domain.dto.application;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.ssafy.ios.lineup.backend.domain.dto.application fileName       :
 * ApplicationRequest author         : moongi date           : 9/16/24 description    :
 */
@Getter
@NoArgsConstructor
public class ApplicationResponse {

    private Long recruitId;
    private Long applicantId;
    private LocalDateTime createdAt;

    @Builder
    public ApplicationResponse(Long recruitId, Long applicantId, LocalDateTime createdAt) {
        this.recruitId = recruitId;
        this.applicantId = applicantId;
        this.createdAt = createdAt;
    }
}
