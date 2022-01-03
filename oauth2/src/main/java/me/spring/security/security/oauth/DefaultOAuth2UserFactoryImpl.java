package me.spring.security.security.oauth;

import me.spring.security.user.UserEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DefaultOAuth2UserFactoryImpl implements DefaultOAuth2UserFactory {

    @Override
    public DefaultOAuth2User of(UserEntity userEntity) {
        return new DefaultOAuth2User(
                userEntity.getRoles()
                        .stream()
                        .map(userRoleEntity -> new SimpleGrantedAuthority(userRoleEntity.getRole().name()))
                        .collect(Collectors.toSet()),
                Map.of(
                        "nickname", Optional.ofNullable(userEntity.getNickName()),
                        "birth", Optional.ofNullable(userEntity.getBirth()),
                        "thumbnail", Optional.ofNullable(userEntity.getThumbnail()),
                        userEntity.getResourceServer().getNameAttribute(), userEntity.getId()
                ),
                userEntity.getResourceServer().getNameAttribute()
        );
    }

}
