package me.spring.security.security;

import me.spring.security.security.testdouble.SpyUserAuthenticationProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static org.assertj.core.api.Assertions.assertThat;

class UserAuthenticationManagerTest {

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

    @Test
    void authenticate_returnsAuthenticatedToken() {
        UserAuthenticationToken givenAuthenticationToken = new UserAuthenticationToken();
        spyUserAuthenticationProvider.authenticate_returns = new UserAuthenticationToken();
        spyUserAuthenticationProvider.authenticate_returns.getUserAuthorities().add(new SimpleGrantedAuthority("USER"));
        UserAuthenticationToken authenticate_returns = userAuthenticationManagerImpl.authenticate(givenAuthenticationToken);
        assertThat(authenticate_returns.isAuthenticated()).isTrue();
    }

}
