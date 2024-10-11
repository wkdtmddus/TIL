package com.ssafy.ios.lineup.backend.application.service.auth;

import static com.ssafy.ios.lineup.backend.common.constant.error_code.UserErrorCode.WRONG_LOGIN_REQUEST;

import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.common.util.FileUtil;
import com.ssafy.ios.lineup.backend.common.util.JWTUtil;
import com.ssafy.ios.lineup.backend.domain.dto.user.CustomUserDetails;
import com.ssafy.ios.lineup.backend.domain.dto.user.OAuth2SignUpRequest;
import com.ssafy.ios.lineup.backend.domain.dto.user.UserDetailResponse;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import com.ssafy.ios.lineup.backend.domain.repository.user.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;
    private final OAuth2UserService oAuth2UserService;
    private final FileUtil fileUtil;

    @Override
    public UserDetailResponse getUserInfo(HttpServletRequest request) {
        User loginUser = getLoginUser();
        if (loginUser == null) {
            return null;
        }
        // TOOD: 유저 정보 추가적으로 받아서 추가해줘야 함.
        UserDetailResponse userDetailDto = UserDetailResponse.builder()
                .userId(loginUser.getId())
                .email(loginUser.getEmail())
                .profileImg(fileUtil.getUserProfileImg(loginUser.getProfileImgFilename()))
                .nickname(loginUser.getNickname())
                .build();

//        userDetailDto.loadUserImg(fileUploadUtil);
        return userDetailDto;
    }

    @Override
    public Long saveOAuth2User(OAuth2SignUpRequest oAuth2SignUpRequest, MultipartFile userImg) {
        return oAuth2UserService.saveOAuth2User(oAuth2SignUpRequest, userImg);
    }

    @Override
    public String getAccessToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = extractTokenFromHeader(request, "refreshToken");

        if (refreshToken == null || jwtUtil.isExpired(refreshToken)) {
            return null;
        }

        String email = jwtUtil.getUsername(refreshToken);
        String role = jwtUtil.getRole(refreshToken);
        log.info("email: {}, role: {}에 해당하는 accessToken을 재발급합니다. ", email, role);
        return jwtUtil.createAccessJwt(email, role);
    }

    //TODO: refreshToken 가져오기 수정
    @Override
    public String getRefreshToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = extractTokenFromHeader(request, "refreshToken");

        if (refreshToken == null || jwtUtil.isExpired(refreshToken)) {
            return null;
        }

        String email = jwtUtil.getUsername(refreshToken);
        String role = jwtUtil.getRole(refreshToken);
        log.info("email: {}, role: {}에 해당하는 refreshToken을 재발급합니다. ", email, role);
        return jwtUtil.createAccessJwt(email, role);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = extractTokenFromHeader(request, "refreshToken");
        if (refreshToken == null) {
            log.info("헤더에서 refreshToken을 찾을 수 없습니다.");
            return;
        }

        String email = jwtUtil.getUsername(refreshToken);
        User user = userRepository.findByEmailAndDeletedAtNull(email)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 이메일의 유저가 없습니다"));

        // 세션 스토리지로 관리 시, 클라이언트 쪽에서 스토리지의 refreshToken을 삭제해야 함.
        log.info("로그아웃 처리 완료 - 사용자: " + email);
    }

    @Override
    public void requestAccess(HttpServletRequest request, HttpServletResponse response) {
        String authorization = extractTokenFromHeader(request, "Authorization");
        if (authorization == null || jwtUtil.isExpired(authorization)) {
            log.info("토큰이 없습니다. 혹은 만료되었습니다");
            return;
        }

        String email = jwtUtil.getUsername(authorization);
        String role = jwtUtil.getRole(authorization);

        // 새로운 accessToken과 refreshToken 생성
        String accessToken = jwtUtil.createAccessJwt(email, role);
//        response.addHeader("Authorization", "Bearer " + accessToken);
        response.addHeader("Authorization", accessToken);

        String refreshToken = jwtUtil.createRefreshJwt(email, role);
//        response.addHeader("refreshToken", "Bearer " + refreshToken);
        response.addHeader("refreshToken", refreshToken);

        User user = userRepository.findByEmailAndDeletedAtNull(email)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 이메일의 유저가 없습니다"));

        // OAuthAuthorization 헤더 삭제 (세션 스토리지에서 클라이언트가 직접 삭제)
        log.info("OAuth 인증 처리 완료 - 사용자: " + email);
    }

    @Override
    public User getLoginUser() throws CustomException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!isLogin(principal)) {
            return null;
        }
        String email = ((CustomUserDetails) principal).getUsername();
        return userRepository.findByEmailAndDeletedAtNull(email)
                .orElseThrow(() -> new CustomException(WRONG_LOGIN_REQUEST));
    }

    @Override
    public boolean isNicknameDuplicate(String nickname) {
        return userRepository.existsByNicknameAndDeletedAtNull(nickname);
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

    private String extractTokenFromHeader(HttpServletRequest request, String headerName) {
        String token = request.getHeader(headerName);
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

    private String extractOAuthAuthorizationFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("OAuthAuthorization".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private void removeOAuthAuthorizationCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("OAuthAuthorization", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60 * 60 * 60);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }
}
