package com.ssafy.ios.lineup.backend.application.service.auth;

import com.ssafy.ios.lineup.backend.domain.dto.user.OAuth2SignUpRequest;
import com.ssafy.ios.lineup.backend.domain.dto.user.UserDetailResponse;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

public interface AuthService {

    UserDetailResponse getUserInfo(HttpServletRequest request);

    Long saveOAuth2User(OAuth2SignUpRequest userCreateDto, MultipartFile userImg);

    String getAccessToken(HttpServletRequest request, HttpServletResponse response);

    String getRefreshToken(HttpServletRequest request, HttpServletResponse response);

    void logout(HttpServletRequest request, HttpServletResponse response);

    void requestAccess(HttpServletRequest request, HttpServletResponse response);

    User getLoginUser(); // Added method

    boolean isNicknameDuplicate(String nickname);
}
