package me.spring.security.security.oauth;

import org.junit.jupiter.api.BeforeEach;

class DefaultOAuth2UserFactoryTest {

    private DefaultOAuth2UserFactoryImpl defaultOauth2UserFactory;

    @BeforeEach
    void setUp() {
        defaultOauth2UserFactory = new DefaultOAuth2UserFactoryImpl();
    }

}
