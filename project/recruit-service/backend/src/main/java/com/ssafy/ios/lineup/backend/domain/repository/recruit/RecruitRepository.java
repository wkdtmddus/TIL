package com.ssafy.ios.lineup.backend.domain.repository.recruit;

import com.ssafy.ios.lineup.backend.domain.entity.Recruit;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitRepository extends
        JpaRepository<Recruit, Long>, RecruitCustomRepository {

    /* 삭제되지 않은 공고만 찾기 */
    Optional<Recruit> findByIdAndDeletedAtNull(Long id);

    /* 삭제된 공고만 찾기 */
    Optional<Recruit> findByIdAndDeletedAtNotNull(Long id);

    List<Recruit> findAll();
}
