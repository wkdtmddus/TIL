package com.ssafy.whoareyou.user.service.implement;

import java.util.Map;
import java.util.Optional;

import com.ssafy.whoareyou.user.entity.Female;
import com.ssafy.whoareyou.user.entity.Male;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.whoareyou.user.entity.CustomOAuth2User;
import com.ssafy.whoareyou.user.entity.User;
import com.ssafy.whoareyou.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImplement extends DefaultOAuth2UserService{

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException{

        OAuth2User oAuth2User = super.loadUser(request);
        String oauthClientName = request.getClientRegistration().getClientName();

        try{
            System.out.println(new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
        } catch (Exception exception){
            exception.printStackTrace();
        }

//        User userEntity = null;
        String email="email@email.com", name, nickname, gender;

        if(oauthClientName.equals("naver")){
            Map<String, String> responseMap = (Map<String, String>) oAuth2User.getAttributes().get("response");
            email = responseMap.get("email");
            name = responseMap.get("name");
            nickname = responseMap.get("nickname");
            gender = responseMap.get("gender").equals("M") ? "male" : "female";

            Optional<User> userEntity = userRepository.findByEmail(email);
            if (userEntity.isEmpty()) {
                // Create new user entity if not exists
                if (gender.equals("male")) {
                    userEntity = Optional.of(new Male(email, name, nickname, "naver"));
                } else {
                    userEntity = Optional.of(new Female(email, name, nickname, "naver"));
                }
                System.out.println("저장시도");
                userRepository.save(userEntity.get());
                System.out.println("저장완료");
            }
        }

        return new CustomOAuth2User(email);
    }
}
