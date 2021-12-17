package me.spring.security.config;

import me.spring.security.config.testdouble.SpyUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.assertj.core.api.Assertions.assertThat;

class AuthenticationProviderTest {

    private AuthenticationProviderImpl authenticationProvider;
    private SpyUserDetailsService spyUserDetailsService;

    @BeforeEach
    void setUp() {
        authenticationProvider = new AuthenticationProviderImpl();
    }

    @Test
    void authenticate_callsLoadUserByUsername_inUserDetailsService() {
        UsernamePasswordAuthenticationToken givenAuthentication = new UsernamePasswordAuthenticationToken("id", "password");
        authenticationProvider.authenticate(givenAuthentication);
        assertThat(spyUserDetailsService.loadUserByUsername_arguments).isEqualTo(givenAuthentication.getPrincipal());
    }
}
