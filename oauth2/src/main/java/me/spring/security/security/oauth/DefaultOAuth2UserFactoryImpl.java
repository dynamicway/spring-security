package me.spring.security.security.oauth;

import me.spring.security.user.UserEntity;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

public class DefaultOAuth2UserFactoryImpl implements DefaultOAuth2UserFactory {

    @Override
    public DefaultOAuth2User of(UserEntity userEntity) {
        return null;
    }

}
