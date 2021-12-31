package me.spring.security.security.oauth;

import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Map;

public interface DefaultOAuth2UserFactory {

    DefaultOAuth2User ofKakao(Map<String, Object> attributes);

}
