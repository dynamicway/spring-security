package me.spring.security.security.oauth.testdouble;

import me.spring.security.security.oauth.DefaultOAuth2UserFactory;
import me.spring.security.user.UserEntity;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

public class SpyDefaultOAuth2UserFactory implements DefaultOAuth2UserFactory {

    public UserEntity of_arguments;

    @Override
    public DefaultOAuth2User of(UserEntity userEntity) {
        of_arguments = userEntity;
        return null;
    }

}
