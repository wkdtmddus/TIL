package com.ssafy.ios.lineup.backend.domain.dto.user;

import java.util.Map;

public class GoogleResponse implements OAuth2Response{

    private final Map<String, Object> attribute;

    public GoogleResponse(final Map<String, Object> attribute) {
//        this.attribute = (Map<String, Object>) attribute.get("response");
        this.attribute = attribute;
    }

    public Map<String, Object> getAttribute() {
        return attribute;
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getProviderId() {
        return attribute.get("sub").toString();
    }

    @Override
    public String getEmail() {
        return attribute.get("email").toString();
    }

    @Override
    public String getName() {
        return attribute.get("name").toString();
    }
}
