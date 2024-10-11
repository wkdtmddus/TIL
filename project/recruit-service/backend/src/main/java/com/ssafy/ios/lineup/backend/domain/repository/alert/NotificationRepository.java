package com.ssafy.ios.lineup.backend.domain.repository.alert;

import com.ssafy.ios.lineup.backend.domain.entity.Notification;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Override
    Optional<Notification> findById(Long id);
}
