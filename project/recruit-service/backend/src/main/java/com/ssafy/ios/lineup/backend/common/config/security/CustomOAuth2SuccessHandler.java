package com.ssafy.ios.lineup.backend.common.config.security;

import com.ssafy.ios.lineup.backend.common.util.JWTUtil;
import com.ssafy.ios.lineup.backend.domain.dto.user.CustomOAuth2User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;

    @Value("${cors.allowedOrigins}")
    private String allowedOrigins;

    @Value("${oauth-token.milli-second}")
    long jwtOAuthTokenExpireTime;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

//        log.info("authentication = {}", authentication);
        log.info("request code = {}", request.getParameter("code"));
//        log.info("response = {}", response.);

        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = customOAuth2User.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority authority = iterator.next();

        String email = customOAuth2User.getEmail();
        String role = authority.getAuthority();
        // 회원가입
        // TODO : 현재 처음으로 소셜 로그인으로 가입한 것인지 판단하는 유무 만들기 (임시로 Role)
        if (customOAuth2User.hasNoRole()) {
            log.info("회원가입 창으로 이동합니다.");
            response.sendRedirect(allowedOrigins + "/signup?email=" + email);
        } else {
            // 이미 회원가입된 유저일 시, 굉장히 짧은 임시 JWT 토큰 발급
            String accessToken = jwtUtil.createTempJwt(email, role);
            log.info("OAuth2에서 임시 JWT를 발급한 뒤 쿠키에 담아 보냈습니다. = {} ", accessToken);
            response.addCookie(createCookie("Authorization", accessToken));


            // 프론트 엔드 주소
            response.sendRedirect(allowedOrigins + "/home");
        }

    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge((int) jwtOAuthTokenExpireTime);
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(false);

        return cookie;
    }
}