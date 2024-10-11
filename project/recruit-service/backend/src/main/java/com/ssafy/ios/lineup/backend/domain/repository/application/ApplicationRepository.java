package com.ssafy.ios.lineup.backend.domain.repository.application;

import com.ssafy.ios.lineup.backend.domain.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findByIdAndDeletedAtNull(Long id);

    List<Application> findByRecruitId(Long recruitId);

    Optional<Application> findByRecruitIdAndApplicantId(Long recruitId, Long applicantId);
}
