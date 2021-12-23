package me.spring.security.security;

import me.spring.security.security.testdouble.SpyUserAuthenticationProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserAuthenticationManagerImplTest {

    private UserAuthenticationManagerImpl userAuthenticationManagerImpl;
    private SpyUserAuthenticationProvider spyUserAuthenticationProvider;

    @BeforeEach
    void setUp() {
        spyUserAuthenticationProvider = new SpyUserAuthenticationProvider();
        userAuthenticationManagerImpl = new UserAuthenticationManagerImpl(spyUserAuthenticationProvider);
    }

    @Test
    void authenticate_callsAuthenticate_inUserAuthenticationProvider() {
        UserAuthenticationToken givenAuthenticationToken = new UserAuthenticationToken();
        userAuthenticationManagerImpl.authenticate(givenAuthenticationToken);
        assertThat(spyUserAuthenticationProvider.authenticate_arguments).isEqualTo(givenAuthenticationToken);
    }

}