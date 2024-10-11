package com.ssafy.ios.lineup.backend.domain.repository.user;

import com.ssafy.ios.lineup.backend.domain.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdAndDeletedAtNull(long id);
    boolean existsByNicknameAndDeletedAtNull(String nickname);

    Optional<User> findByEmailAndDeletedAtNull(String email);



}
