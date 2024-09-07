package com.sparta.toogo.domain.kakao.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.toogo.domain.kakao.dto.KakaoUserInfoDto;
import com.sparta.toogo.domain.mypage.entity.MyPage;
import com.sparta.toogo.domain.mypage.repository.MyPageRepository;
import com.sparta.toogo.domain.user.entity.EmoticonEnum;
import com.sparta.toogo.domain.user.entity.User;
import com.sparta.toogo.domain.user.entity.UserRoleEnum;
import com.sparta.toogo.domain.user.exception.UserException;
import com.sparta.toogo.domain.user.repository.UserRepository;
import com.sparta.toogo.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

import static com.sparta.toogo.global.enums.ErrorCode.DUPLICATE_EMAIL;

@Slf4j(topic = "KAKAO Login")
@Service
@RequiredArgsConstructor
public class KakaoService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final JwtUtil jwtUtil;
    private final MyPageRepository myPageRepository;

    @Transactional
    public User kakaoLogin(String code) throws JsonProcessingException {
        // 1. "인가 코드"로 "액세스 토큰" 요청
        String accessToken = getToken(code);
        // 2. 토큰으로 카카오 API 호출 : "액세스 토큰"으로 "카카오 사용자 정보" 가져오기
        KakaoUserInfoDto kakaoUserInfo = getKakaoUserInfo(accessToken);
        // 3. 필요시에 회원가입
        return registerKakaoUserIfNeeded(kakaoUserInfo);
    }

    private String getToken(String code) throws JsonProcessingException {
        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("https://kauth.kakao.com")
                .path("/oauth/token")
                .encode()
                .build()
                .toUri();

        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "564f00b46533ce881c7fe7c870c83458");
//        body.add("redirect_uri", "http://localhost:3000/api/auth/kakao");
//        body.add("redirect_uri", "http://oeoe.s3-website.ap-northeast-2.amazonaws.com/api/auth/kakao");
        body.add("redirect_uri", "https://oetrip.site/api/auth/kakao");
        body.add("code", code);

        RequestEntity<MultiValueMap<String, String>> requestEntity = RequestEntity
                .post(uri)
                .headers(headers)
                .body(body);

        // HTTP 요청 보내기
        ResponseEntity<String> response = restTemplate.exchange(
                requestEntity,
                String.class
        );

        // HTTP 응답 (JSON) -> 액세스 토큰 파싱
        JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
        return jsonNode.get("access_token").asText();
    }

    private KakaoUserInfoDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("https://kapi.kakao.com")
                .path("/v2/user/me")
                .encode()
                .build()
                .toUri();

        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        RequestEntity<MultiValueMap<String, String>> requestEntity = RequestEntity
                .post(uri)
                .headers(headers)
                .body(new LinkedMultiValueMap<>());

        // HTTP 요청 보내기
        ResponseEntity<String> response = restTemplate.exchange(
                requestEntity,
                String.class
        );
        log.info(response.getBody());
        JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
        Long id = jsonNode.get("id").asLong();
        String nickname = jsonNode.get("properties")
                .get("nickname").asText();
        if (jsonNode.get("kakao_account").get("email") == null) {
            return new KakaoUserInfoDto(id, nickname);
        }
        String email = jsonNode.get("kakao_account")
                .get("email").asText();

        log.info("카카오 사용자 정보: " + id + ", " + nickname + ", " + email);
        return new KakaoUserInfoDto(id, email, nickname);
    }

    private User registerKakaoUserIfNeeded(KakaoUserInfoDto kakaoUserInfo) {
        // DB 에 중복된 Kakao Id 가 있는지 확인
        Long kakaoId = kakaoUserInfo.getId();
        User kakaoUser = userRepository.findByKakaoId(kakaoId).orElse(null);

        if (kakaoUser == null) {
            // 신규 회원가입
            String password = UUID.randomUUID().toString();
            String encodedPassword = passwordEncoder.encode(password);
            String email = kakaoUserInfo.getEmail();

            kakaoUser = new User(email, encodedPassword, kakaoUserInfo.getNickname(), UserRoleEnum.USER, kakaoId, EmoticonEnum.HAPPY.getEmoticon());
            userRepository.save(kakaoUser);
            MyPage myPage = new MyPage();
            myPage.setUser(kakaoUser);
            myPageRepository.save(myPage);
        }
        return kakaoUser;
    }

    public String kakaoAccessToken(User user) {
        return jwtUtil.createAccessToken(user.getId(), user.getRole());
    }

    public String kakaoRefreshToken(User user) {
        return jwtUtil.createRefreshToken(user.getId());
    }
}