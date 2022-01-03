package me.spring.security.security.oauth;

import me.spring.security.security.oauth.testdouble.SpyDefaultOAuth2UserFactory;
import me.spring.security.security.oauth.testdouble.SpyDefaultOAuth2UserService;
import me.spring.security.security.oauth.testdouble.SpyUserRepository;
import me.spring.security.user.UserEntity;
import me.spring.security.user.UserRoleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.AuthenticationMethod;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class CustomOAuth2UserServiceTest {

    private CustomOAuth2UserService customOAuth2UserService;
    private SpyUserRepository spyUserRepository;
    private SpyDefaultOAuth2UserService spyDefaultOAuth2UserService;
    private SpyDefaultOAuth2UserFactory spyDefaultOAuth2UserFactory;

    @BeforeEach
    void setUp() {
        spyDefaultOAuth2UserService = new SpyDefaultOAuth2UserService();
        spyUserRepository = new SpyUserRepository();
        spyDefaultOAuth2UserFactory = new SpyDefaultOAuth2UserFactory();
        customOAuth2UserService = new CustomOAuth2UserService(
                spyDefaultOAuth2UserService,
                spyUserRepository,
                spyDefaultOAuth2UserFactory
        );
    }

    @Test
    void loadUser_callsFindByResourceServerIdAndResourceServerName_inUserRepository() {
        OAuth2UserRequest givenOAuth2UserRequest = createOAuth2UserRequestDummy("kakao", "id");

        spyUserRepository.findByResourceServerAndResourceServerId_returns = Optional.of(new UserEntity());
        spyDefaultOAuth2UserService.loadUser_returns = createDefaultOAuth2UserDummy("id", "1");

        customOAuth2UserService.loadUser(givenOAuth2UserRequest);
        assertThat(spyUserRepository.findByResourceServerAndResourceServerId_argumentsResourceServer).isEqualTo(ResourceServer.KAKAO);
        assertThat(spyUserRepository.findByResourceServerAndResourceServerId_argumentsResourceServerId).isEqualTo("1");
    }

    @Test
    void loadUser_callsLoadUser_inDefaultOAuth2UserService() {
        OAuth2UserRequest givenOAuth2UserRequest = createOAuth2UserRequestDummy("kakao", "id");
        spyUserRepository.findByResourceServerAndResourceServerId_returns = Optional.of(new UserEntity());
        spyDefaultOAuth2UserService.loadUser_returns = createDefaultOAuth2UserDummy("id", "1");
        customOAuth2UserService.loadUser(givenOAuth2UserRequest);
        assertThat(spyDefaultOAuth2UserService.loadUser_arguments).isEqualTo(givenOAuth2UserRequest);
    }

    @Test
    void loadUser_callsSave_inUserRepository_when_notExistsUser() {
        OAuth2UserRequest givenOAuth2UserRequest = createOAuth2UserRequestDummy("kakao", "id");

        spyUserRepository.findByResourceServerAndResourceServerId_returns = Optional.empty();
        spyDefaultOAuth2UserService.loadUser_returns = createDefaultOAuth2UserDummy("id", "1");

        customOAuth2UserService.loadUser(givenOAuth2UserRequest);
        assertThat(spyUserRepository.save_arguments).isEqualTo(new UserEntity(
                ResourceServer.KAKAO,
                "1",
                "given_profile_image",
                List.of(new UserRoleEntity(UserRoleEntity.Role.USER))
        ));
    }

    @Test
    void loadUser_callsOf_inDefaultOAuth2UserFactory() {
        OAuth2UserRequest givenOAuth2UserRequest = createOAuth2UserRequestDummy("kakao", "id");

        spyUserRepository.findByResourceServerAndResourceServerId_returns = Optional.of(new UserEntity());
        spyDefaultOAuth2UserService.loadUser_returns = createDefaultOAuth2UserDummy("id", "1");

        customOAuth2UserService.loadUser(givenOAuth2UserRequest);
        assertThat(spyDefaultOAuth2UserFactory.of_arguments).isEqualTo(spyUserRepository.findByResourceServerAndResourceServerId_returns.get());
    }

    private DefaultOAuth2User createDefaultOAuth2UserDummy(String nameAttributeKey, String nameAttributeValue) {
        return new DefaultOAuth2User(List.of(new SimpleGrantedAuthority("USER")), Map.of(nameAttributeKey, nameAttributeValue,
                "profile_image", "given_profile_image"), nameAttributeKey);
    }

    private OAuth2UserRequest createOAuth2UserRequestDummy(String registrationId, String userNameAttributeName) {
        ClientRegistration givenClientRegistration = ClientRegistration
                .withRegistrationId(registrationId)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .clientId("clientId")
                .clientSecret("clientSecret")
                .redirectUri("redirectUri")
                .authorizationUri("authorizationUri")
                .tokenUri("tokenUri")
                .userInfoUri("userInfoUri")
                .userInfoAuthenticationMethod(AuthenticationMethod.HEADER)
                .userNameAttributeName(userNameAttributeName)
                .build();
        OAuth2AccessToken givenOAuth2AccessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, "tokenValue", Instant.now(), Instant.now());
        return new OAuth2UserRequest(givenClientRegistration, givenOAuth2AccessToken);
    }

}
