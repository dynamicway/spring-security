package me.spring.security.security.oauth;

import me.spring.security.user.UserEntity;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

public interface DefaultOAuth2UserFactory {

    DefaultOAuth2User of(UserEntity userEntity);

}
