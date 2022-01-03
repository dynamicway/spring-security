package me.spring.security.security.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.Map;

class JwtManagerTest {

    private final JwtManager jwtManager = new JwtManagerImpl();

    @Test
    void generateJwt() {
        OAuth2User givenOAuth2User = new DefaultOAuth2User(
                List.of(new SimpleGrantedAuthority("USER")),
                Map.of(),
                "id"
        );
        jwtManager.generateJwt(givenOAuth2User);
    }

}