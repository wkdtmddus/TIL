package com.ssafy.ios.lineup.backend.domain.repository.recruit;

import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitCardResponse;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitDetailResponse;
import com.ssafy.ios.lineup.backend.domain.dto.recruit.RecruitSearchFilter;
import com.ssafy.ios.lineup.backend.domain.entity.Recruit;
import java.time.LocalDateTime;
import java.util.List;

/**
 * packageName    : com.ssafy.ios.lineup.backend.domain.repository.recruit fileName       :
 * RecruitCustomRepository author         : moongi date           : 9/16/24 description    :
 */
public interface RecruitCustomRepository {

    RecruitDetailResponse findRecruitDetailById(Long recruitId);

    List<Recruit> findExpiredRecruits(LocalDateTime now);

    /* 검색 결과에 따른 공고 조회 */
    List<RecruitCardResponse> searchRecruitByFilter(RecruitSearchFilter recruitSearchFilter);

    /* 모든 공고 조회 */
    List<RecruitCardResponse> getRecruits();


}
