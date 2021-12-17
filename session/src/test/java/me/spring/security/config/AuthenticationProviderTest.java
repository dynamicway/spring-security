package me.spring.security.config;

import me.spring.security.config.testdouble.SpyUserDetailsService;
import me.spring.security.error.BadCredentialException;
import me.spring.security.user.UserEntity;
import me.spring.security.user.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AuthenticationProviderTest {

    private AuthenticationProviderImpl authenticationProvider;
    private SpyUserDetailsService spyUserDetailsService;

    @BeforeEach
    void setUp() {
        spyUserDetailsService = new SpyUserDetailsService();
        authenticationProvider = new AuthenticationProviderImpl(spyUserDetailsService);
    }

    private UserDetailsImpl createUserDetailsImpl() {
        UserEntity userEntity = new UserEntity(
                1L,
                "name",
                "password",
                "email"
        );
        userEntity.getRoles().add(new UserRole(
                1L,
                UserRole.Role.USER
        ));
        return new UserDetailsImpl(
                userEntity
        );
    }

    @Test
    void authenticate_callsLoadUserByUsername_inUserDetailsService() {
        UsernamePasswordAuthenticationToken givenAuthentication = new UsernamePasswordAuthenticationToken("id", "password");
        spyUserDetailsService.loadUserByUsername_returns = createUserDetailsImpl();
        authenticationProvider.authenticate(givenAuthentication);
        assertThat(spyUserDetailsService.loadUserByUsername_arguments).isEqualTo(givenAuthentication.getPrincipal());
    }

    @Test
    void authenticate_returnsAuthenticatedToken() {
        UsernamePasswordAuthenticationToken givenAuthentication = new UsernamePasswordAuthenticationToken("id", "password");
        spyUserDetailsService.loadUserByUsername_returns = createUserDetailsImpl();
        Authentication authenticate_returns = authenticationProvider.authenticate(givenAuthentication);
        assertThat(authenticate_returns.isAuthenticated()).isTrue();
        assertThat(authenticate_returns.getPrincipal()).isInstanceOf(UserDetailsImpl.class);
        assertThat(authenticate_returns.getPrincipal()).isEqualTo(spyUserDetailsService.loadUserByUsername_returns);
        assertThat(authenticate_returns.getCredentials()).isEqualTo(givenAuthentication.getCredentials());
    }

    @Test
    void authenticate_throwsAuthenticationException_when_invalidPassword() {
        UsernamePasswordAuthenticationToken givenAuthentication = new UsernamePasswordAuthenticationToken("id", "invalidPassword");
        spyUserDetailsService.loadUserByUsername_returns = createUserDetailsImpl();
        assertThatThrownBy(() -> authenticationProvider.authenticate(givenAuthentication))
                .isInstanceOf(BadCredentialException.class)
                .hasMessage("invalid password");
    }

    @Test
    void supports_returnsTrue_when_arguments_instanceOfUsernamePasswordAuthenticationToken() {
        boolean supports_returns = authenticationProvider.supports(UsernamePasswordAuthenticationToken.class);
        assertThat(supports_returns).isTrue();
    }

    @Test
    void supports_returnsFalse_when_arguments_notInstanceOfUsernamePasswordAuthenticationToken() {
        boolean supports_returns = authenticationProvider.supports(Authentication.class);
        assertThat(supports_returns).isFalse();
    }

}
