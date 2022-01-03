package me.spring.security.security.oauth.testdouble;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class SpyDefaultOAuth2UserService extends DefaultOAuth2UserService {

    public OAuth2UserRequest loadUser_arguments;
    public OAuth2User loadUser_returns;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        loadUser_arguments = userRequest;
        return loadUser_returns;
    }

}
