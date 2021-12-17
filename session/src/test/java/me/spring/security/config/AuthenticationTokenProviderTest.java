package me.spring.security.config;

import me.spring.security.config.testdouble.SpyAuthenticationManager;
import me.spring.security.error.BadRequestException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class AuthenticationTokenProviderTest {

    private SpyAuthenticationManager spyAuthenticationManager;
    private AuthenticationTokenProvider authenticationTokenProvider;

    public static Stream<Arguments> arguments_attemptAuthentication_throwsBadRequestException_when_idOrPasswordIsNull() {
        return Stream.of(
                arguments(null, "password"),
                arguments("id", null),
                arguments(null, null)
        );
    }

    @BeforeEach
    void setUp() {
        spyAuthenticationManager = new SpyAuthenticationManager();
        authenticationTokenProvider = new AuthenticationTokenProvider(spyAuthenticationManager);
    }

    @ParameterizedTest
    @MethodSource("arguments_attemptAuthentication_throwsBadRequestException_when_idOrPasswordIsNull")
    void attemptAuthentication_when_idOrPasswordIsNull(String id, String password) {
        MockHttpServletRequest givenRequest = new MockHttpServletRequest();
        givenRequest.addParameter("id", id);
        givenRequest.addParameter("password", password);
        Assertions.assertThatThrownBy(() -> authenticationTokenProvider.attemptAuthentication(givenRequest, new MockHttpServletResponse()))
                .isInstanceOf(BadRequestException.class);
    }

    @Test
    void attemptAuthentication_returnsUsernamePasswordAuthenticationToken_when_idAndPasswordIsNotNull() {
        MockHttpServletRequest givenRequest = new MockHttpServletRequest();
        givenRequest.addParameter("id", "id");
        givenRequest.addParameter("password", "password");
        spyAuthenticationManager.authenticate_returns = new UsernamePasswordAuthenticationToken("id", "password");
        Authentication authentication = authenticationTokenProvider.attemptAuthentication(givenRequest, new MockHttpServletResponse());
        assertThat(authentication).isInstanceOf(UsernamePasswordAuthenticationToken.class);
        assertThat(authentication.getPrincipal()).isEqualTo("id");
        assertThat(authentication.getCredentials()).isEqualTo("password");
        assertThat(authentication.isAuthenticated()).isFalse();
    }

    @Test
    void attemptAuthentication_callsAuthenticate_inAuthenticationManager() {
        MockHttpServletRequest givenRequest = new MockHttpServletRequest();
        givenRequest.addParameter("id", "id");
        givenRequest.addParameter("password", "password");
        authenticationTokenProvider.attemptAuthentication(givenRequest, new MockHttpServletResponse());
        assertThat(spyAuthenticationManager.authenticate_argumentsAuthentication).isInstanceOf(UsernamePasswordAuthenticationToken.class);
        assertThat(spyAuthenticationManager.authenticate_argumentsAuthentication.getPrincipal()).isEqualTo("id");
        assertThat(spyAuthenticationManager.authenticate_argumentsAuthentication.getCredentials()).isEqualTo("password");
        assertThat(spyAuthenticationManager.authenticate_argumentsAuthentication.isAuthenticated()).isFalse();
    }

}
