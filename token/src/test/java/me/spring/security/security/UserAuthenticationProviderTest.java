package me.spring.security.security;

import org.junit.jupiter.api.BeforeEach;

class UserAuthenticationProviderTest {

    private UserAuthenticationProviderImpl userAuthenticationProvider;

    @BeforeEach
    void setUp() {
        userAuthenticationProvider = new UserAuthenticationProviderImpl();
    }

}
