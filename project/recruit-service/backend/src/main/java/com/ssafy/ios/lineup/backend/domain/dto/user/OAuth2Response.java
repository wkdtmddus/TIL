package com.ssafy.ios.lineup.backend.domain.dto.user;

/**
 * packageName    : com.ssafy.ios.lineup.backend.domain.dto.user
 * fileName       : OAuth2Response
 * author         : moongi
 * date           : 9/27/24
 * description    :
 */
public interface OAuth2Response {

    String getProvider();

    String getProviderId();

    String getEmail();

    String getName();
}
