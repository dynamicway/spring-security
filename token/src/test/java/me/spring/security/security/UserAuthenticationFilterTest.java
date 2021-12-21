package me.spring.security.security;

import me.spring.security.security.testdouble.SpyUserAuthenticationFactory;
import me.spring.security.security.testdouble.SpyUserAuthenticationManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;

class UserAuthenticationFilterTest {

    private UserAuthenticationFilter userAuthenticationFilter;
    private SpyUserAuthenticationFactory spyUserAuthenticationFactory;
    private SpyUserAuthenticationManager spyUserAuthenticationManager;

    @BeforeEach
    void setUp() {
        spyUserAuthenticationFactory = new SpyUserAuthenticationFactory();
        spyUserAuthenticationManager = new SpyUserAuthenticationManager();
        userAuthenticationFilter = new UserAuthenticationFilter(
                spyUserAuthenticationManager,
                spyUserAuthenticationFactory
        );
    }

    @Test
    void attemptAuthentication_callsUnAuthenticatedToken_inUserAuthenticationTokenFactory() {
        MockHttpServletRequest givenHttpRequest = new MockHttpServletRequest();
        userAuthenticationFilter.attemptAuthentication(givenHttpRequest, null);
        assertThat(spyUserAuthenticationFactory.unAuthenticatedToken_arguments).isEqualTo(givenHttpRequest);
    }

    @Test
    void attemptAuthentication_callsAuthenticate_inUserAuthenticationManager() {
        MockHttpServletRequest givenHttpRequest = new MockHttpServletRequest();
        spyUserAuthenticationFactory.unAuthenticatedToken_returns = new UserAuthenticationToken();
        userAuthenticationFilter.attemptAuthentication(givenHttpRequest, null);
        assertThat(spyUserAuthenticationManager.authenticate_arguments).isInstanceOf(UserAuthenticationToken.class);
    }

}
