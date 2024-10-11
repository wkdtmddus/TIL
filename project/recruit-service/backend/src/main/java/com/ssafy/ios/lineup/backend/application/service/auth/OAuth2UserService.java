package com.ssafy.ios.lineup.backend.application.service.auth;

import com.ssafy.ios.lineup.backend.common.exception.CustomException;
import com.ssafy.ios.lineup.backend.common.util.FileUtil;
import com.ssafy.ios.lineup.backend.domain.dto.user.CustomOAuth2User;
import com.ssafy.ios.lineup.backend.domain.dto.user.GoogleResponse;
import com.ssafy.ios.lineup.backend.domain.dto.user.KakaoResponse;
import com.ssafy.ios.lineup.backend.domain.dto.user.NaverResponse;
import com.ssafy.ios.lineup.backend.domain.dto.user.OAuth2Response;
import com.ssafy.ios.lineup.backend.domain.dto.user.OAuth2SignUpRequest;
import com.ssafy.ios.lineup.backend.domain.entity.User;
import com.ssafy.ios.lineup.backend.domain.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final FileUtil fileUtil;

    // OAuth2로 유저 등록하기
    public Long saveOAuth2User(OAuth2SignUpRequest oAuth2SignUpRequest, MultipartFile userImg)
            throws CustomException {
        log.info("Email = {}", oAuth2SignUpRequest.getEmail());
        User findUser = userRepository.findByEmailAndDeletedAtNull(oAuth2SignUpRequest.getEmail())
                .orElse(null);

        if (findUser != null) {
            log.info("Existing user found: Updating user info.");
            // 기존 유저 정보를 업데이트
            findUser.OAuth2Update(User.builder()
                    .role("ROLE_USER")
                    .nickname(oAuth2SignUpRequest.getNickname())
                    .email(oAuth2SignUpRequest.getEmail())
                    .build());

            // 프로필 이미지가 있으면 업데이트
            if (userImg != null && !userImg.isEmpty()) {
                String uuid = UUID.randomUUID().toString();
                fileUtil.saveUserProfileImg(userImg, uuid);
                findUser.updateUserImg(uuid + "." + fileUtil.getExtension(userImg));
            }

            userRepository.save(findUser); // 기존 유저 저장
//            userRepository.flush();
            return findUser.getId();
        } else {
            log.info("No existing user found: Creating a new user.");
            // 새로운 유저 생성
            User newUser = User.builder()
                    .role("ROLE_USER")
                    .nickname(oAuth2SignUpRequest.getNickname())
                    .email(oAuth2SignUpRequest.getEmail())
                    .build();

            // 프로필 이미지가 있으면 설정
            if (userImg != null && !userImg.isEmpty()) {
                String uuid = UUID.randomUUID().toString();
                fileUtil.saveUserProfileImg(userImg, uuid);
                newUser.updateUserImg(uuid + "." + fileUtil.getExtension(userImg));
            }

            userRepository.save(newUser); // 새 유저 저장
            return newUser.getId();
        }

        // TODO: ROLE 수정 필요
//        String role = "ROLE_USER";
//        User TempOAuthUser = User.builder()
//                .role(role)
//                .nickname(oAuth2SignUpRequest.getNickname())
//                .email(oAuth2SignUpRequest.getEmail())
//                .build();
////        findUser.OAuth2Update(TempOAuthUser);
//        TempOAuthUser.OAuth2Update(TempOAuthUser);
//        if (userImg != null && !userImg.isEmpty()) {
//            String uuid = UUID.randomUUID().toString();
//            fileUtil.saveUserProfileImg(userImg, uuid); // 유저 프로필 사진 저장
//
////            findUser.updateUserImg(
//            TempOAuthUser.updateUserImg(
//                    uuid + "." + fileUtil.getExtension(userImg));
//        }
//
//        userRepository.save(TempOAuthUser);
//
////        return findUser.getId();
//        return TempOAuthUser.getId();
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 요청받은 oAuthUser 가져오기 및 응답 만들기
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("클라이언트가 접근한 OAuth2 user입니다. = {}", oAuth2User.toString());
        OAuth2Response oAuth2Response = getOAuth2Response(userRequest, oAuth2User);

        // 유저 정보 처리하기
        String provider = oAuth2Response.getProvider() + "-" + oAuth2Response.getProviderId();
        String email = oAuth2Response.getEmail();
        log.info("OAuth2에서 받은 email입니다. = {}", email);
        Optional<User> findUser = userRepository.findByEmailAndDeletedAtNull(email);

        // 추가 회원가입 처리 유무 판단
        // TODO : 임시로 ROlE의 유무로 계정 로그인 판단하기
        if (findUser.isEmpty()) {
            // 해당 이메일이 없는 경우 -> 회원가입 진행하기
            log.info("해당 이메일로 가입된 계정이 없습니다. = {}", oAuth2Response.getEmail());
            User newUser = User.builder()
                    // TODO : 추가 정보 있으면 넘기기
                    .email(oAuth2Response.getEmail())
//                    .role("ROLE_TEMP") // 없으면 회원가입 필요
                    .provider(provider)
                    .build();
            userRepository.save(newUser); // 이후에 가입으로 추가 업데이트 하기
            return new CustomOAuth2User(newUser);

        } else {
            log.info("해당 이메일로 가입된 계정이 있습니다. = {}", oAuth2Response.getEmail());
            return new CustomOAuth2User(findUser.get()); // Provider에게 제공
        }
    }

    private OAuth2Response getOAuth2Response(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        if (registrationId.equals("naver")) {
            return new NaverResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("google")) {
            return new GoogleResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("kakao")) {
            return new KakaoResponse(oAuth2User.getAttributes());
        }
        throw new OAuth2AuthenticationException("Unsupported OAuth2 service");
    }
}
