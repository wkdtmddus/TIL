package com.sparta.toogo.domain.user.repository;

import com.sparta.toogo.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    User findByNickname(String nickname);

    Optional<User> findByEmail(String email);

    Optional<User> findByKakaoId(Long kakaoId);
}