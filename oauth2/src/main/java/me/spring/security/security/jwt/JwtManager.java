package me.spring.security.security.jwt;

import org.springframework.security.oauth2.core.user.OAuth2User;

public interface JwtManager {

    Jwt generateJwt(OAuth2User oAuth2User);

    void valid(String authorization);

}
