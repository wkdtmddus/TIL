package com.ssafy.ios.lineup.backend.domain.repository.pay;

import com.ssafy.ios.lineup.backend.domain.entity.User;
import com.ssafy.ios.lineup.backend.domain.entity.UserCash;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCashRepository extends JpaRepository<UserCash, Long> {

    Optional<UserCash> findByUser(User user);
}
