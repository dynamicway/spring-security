package me.spring.security.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

class UserAuthenticationTokenFactoryTest {

    private UserAuthenticationTokenFactoryImpl userAuthenticationTokenFactory;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        userAuthenticationTokenFactory = new UserAuthenticationTokenFactoryImpl(objectMapper);
    }

    @Test
    void unAuthenticationToken_returnsUserAuthenticationToken() {
        MockHttpServletRequest givenHttpRequest = new MockHttpServletRequest();
        String givenHttpBody = "{" +
                "\"email\": \"givenEmail\"," +
                "\"password\": \"givenPassword\"" +
                "}";
        givenHttpRequest.setContent(givenHttpBody.getBytes(StandardCharsets.UTF_8));
        givenHttpRequest.setContentType(MediaType.APPLICATION_JSON.getType());

        UserAuthenticationToken of_returns = userAuthenticationTokenFactory.unAuthenticatedToken(givenHttpRequest);

        assertThat(of_returns.getEmail()).isEqualTo("givenEmail");
        assertThat(of_returns.getPassword()).isEqualTo("givenPassword");
    }
}
