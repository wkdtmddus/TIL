package com.ssafy.ios.lineup.backend.domain.repository.recruit;

import com.ssafy.ios.lineup.backend.domain.entity.LikeRecruit;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRecruitRepository extends JpaRepository<LikeRecruit, Long> {

    boolean existsByRecruitIdAndUser(Long recruitId, User user);

    void deleteByRecruitId(Long recruitId);

    Long countByRecruitId(Long recruitId);
}
