package com.ssafy.ios.lineup.backend.application.service.auth;

import com.ssafy.ios.lineup.backend.domain.dto.user.CustomUserDetails;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import com.ssafy.ios.lineup.backend.domain.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User userData = userRepository.findByEmailAndDeletedAtNull(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("요청한 이메일에 해당하는 유저가 없습니다: " + email));
        log.info("요청한 유저 데이터: {}", userData.getEmail());

        return new CustomUserDetails(userData); // AuthenticationManger 에게 보냄
    }
}
