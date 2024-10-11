package com.ssafy.ios.lineup.backend.application.service.user;

import static com.ssafy.ios.lineup.backend.common.constant.error_code.UserErrorCode.NO_USER;
import static com.ssafy.ios.lineup.backend.common.constant.error_code.UserErrorCode.WRONG_LOGIN_REQUEST;

import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.common.validator.UserValidator;
import com.ssafy.ios.lineup.backend.domain.dto.user.CustomUserDetails;
import com.ssafy.ios.lineup.backend.domain.dto.user.UserProfileResponse;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import com.ssafy.ios.lineup.backend.domain.repository.user.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    @Override
    public User selectLoginUser() throws CustomException {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!isLogin(principal)) {
            return null;
        }
        String email = ((CustomUserDetails) principal).getUsername();
        return userRepository.findByEmailAndDeletedAtNull(email)
                .orElseThrow(() -> new CustomException(WRONG_LOGIN_REQUEST));
    }

    private boolean isLogin(Object principal) {
        return principal instanceof CustomUserDetails;
    }

    private String extractRefreshTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refreshToken")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }


    @Override
    public User selectUserById(Long id) throws CustomException {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException(NO_USER));
    }

    @Override
    public UserProfileResponse searchUserProfileById(Long userId) throws CustomException {
        User user = selectUserById(userId);
        userValidator.checkUserNonNull(user);
        return new UserProfileResponse(user);
    }
}
