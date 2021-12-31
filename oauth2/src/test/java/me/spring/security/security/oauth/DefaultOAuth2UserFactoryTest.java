package me.spring.security.security.oauth;

import me.spring.security.user.UserEntity;
import me.spring.security.user.UserRoleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultOAuth2UserFactoryTest {

    private DefaultOAuth2UserFactoryImpl defaultOauth2UserFactory;

    @BeforeEach
    void setUp() {
        defaultOauth2UserFactory = new DefaultOAuth2UserFactoryImpl();
    }

    @Test
    void of_kakao() {
        DefaultOAuth2User of_returns = defaultOauth2UserFactory.of(new UserEntity(
                ResourceServer.KAKAO,
                "1",
                "thumbnail",
                List.of(new UserRoleEntity(UserRoleEntity.Role.USER))
        ));
        assertThat(of_returns.getName()).isEqualTo("1");
        assertThat(of_returns.getAttributes().keySet()).containsExactlyInAnyOrder(
                "nickname",
                "thumbnail",
                "birth",
                ResourceServer.KAKAO.getNameAttribute()
        );
    }

}
