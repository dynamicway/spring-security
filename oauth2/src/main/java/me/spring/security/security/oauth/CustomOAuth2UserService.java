package me.spring.security.security.oauth;

import lombok.RequiredArgsConstructor;
import me.spring.security.user.UserEntity;
import me.spring.security.user.UserRepository;
import me.spring.security.user.UserRoleEntity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final DefaultOAuth2UserService defaultOAuth2UserService;
    private final UserRepository userRepository;
    private final DefaultOAuth2UserFactory defaultOAuth2UserFactory;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = defaultOAuth2UserService.loadUser(userRequest);
        UserEntity userEntity = getOrSaveUserEntity(userRequest, oAuth2User);
        return defaultOAuth2UserFactory.of(userEntity);
    }

    private UserEntity getOrSaveUserEntity(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {

        ResourceServer resourceServer = ResourceServer.getResourceServer(userRequest.getClientRegistration().getRegistrationId());

        String resourceServerId = oAuth2User.getAttribute(resourceServer.getNameAttribute()).toString();
        return userRepository.findByResourceServerAndResourceServerId(resourceServer, resourceServerId)
                .orElse(userRepository.save(new UserEntity(
                        resourceServer,
                        resourceServerId,
                        oAuth2User.getAttribute(resourceServer.getThumbnail()),
                        List.of(new UserRoleEntity(UserRoleEntity.Role.USER))
                )));
    }

}
