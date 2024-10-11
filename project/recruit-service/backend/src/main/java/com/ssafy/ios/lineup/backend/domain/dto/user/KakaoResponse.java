package com.ssafy.ios.lineup.backend.domain.dto.user;

import java.util.Map;

/**
 * packageName    : com.ssafy.ios.lineup.backend.domain.dto.user
 * fileName       : KakaoResponse
 * author         : moongi
 * date           : 10/2/24
 * description    :
 */
public class KakaoResponse implements OAuth2Response{
    private Map<String, Object> attributes;

    private Map<String, Object> attributeAccount;

    private Map<String, Object> attributeProfile;

    public KakaoResponse(Map<String, Object> attributes) {
        this.attributes = attributes;
        this.attributeAccount = (Map<String, Object>) attributes.get("kakao_account");
        this.attributeProfile = (Map<String, Object>) attributeAccount.get("profile");
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getEmail() {
        return attributeAccount.get("email").toString();
    }

    @Override
    public String getName() {
        return attributeProfile.get("nickname").toString();
    }
}
