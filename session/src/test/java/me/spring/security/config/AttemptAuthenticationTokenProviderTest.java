package me.spring.security.config;

import me.spring.security.error.BadRequestException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.stream.Stream;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class AttemptAuthenticationTokenProviderTest {

    private final AttemptAuthenticationTokenProvider attemptAuthenticationTokenProvider = new AttemptAuthenticationTokenProvider(null);

    public static Stream<Arguments> arguments_attemptAuthentication_throwsBadRequestException_when_idOrPasswordIsNull() {
        return Stream.of(
                arguments(null, "password"),
                arguments("id", null),
                arguments(null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments_attemptAuthentication_throwsBadRequestException_when_idOrPasswordIsNull")
    void attemptAuthentication_when_idOrPasswordIsNull(String id, String password) {
        MockHttpServletRequest givenRequest = new MockHttpServletRequest();
        givenRequest.addParameter("id", id);
        givenRequest.addParameter("password", password);
        Assertions.assertThatThrownBy(() -> attemptAuthenticationTokenProvider.attemptAuthentication(givenRequest, new MockHttpServletResponse()))
                .isInstanceOf(BadRequestException.class);
    }

    @Test
    void attemptAuthentication_returnsUsernamePasswordAuthenticationToken_when_idAndPasswordIsNotNull() {
        MockHttpServletRequest givenRequest = new MockHttpServletRequest();
        givenRequest.addParameter("id", "id");
        givenRequest.addParameter("password", "password");
        Authentication authentication = attemptAuthenticationTokenProvider.attemptAuthentication(givenRequest, new MockHttpServletResponse());
        assertSoftly(s -> {
            s.assertThat(authentication).isInstanceOf(UsernamePasswordAuthenticationToken.class);
            s.assertThat(authentication.getPrincipal()).isEqualTo("id");
            s.assertThat(authentication.getCredentials()).isEqualTo("password");
            s.assertThat(authentication.isAuthenticated()).isFalse();
        });
    }

}
