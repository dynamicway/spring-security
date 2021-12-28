package me.spring.security.security;

import me.spring.security.security.testdouble.SpyJwtTokenManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

class UserAuthenticationSuccessHandlerTest {

    private UserAuthenticationSuccessHandler userAuthenticationSuccessHandler;
    private SpyJwtTokenManager spyJwtTokenManager;

    @BeforeEach
    void setUp() {
        spyJwtTokenManager = new SpyJwtTokenManager();
        userAuthenticationSuccessHandler = new UserAuthenticationSuccessHandler(spyJwtTokenManager);
    }

    @Test
    void onAuthenticationSuccess_callsGenerateJwt_inJwtTokenManager() {
        MockHttpServletRequest givenHttpRequest = new MockHttpServletRequest();
        UserAuthenticationToken givenUserAuthenticationToken = new UserAuthenticationToken();
        userAuthenticationSuccessHandler.onAuthenticationSuccess(givenHttpRequest, new MockHttpServletResponse(), givenUserAuthenticationToken);
        assertThat(spyJwtTokenManager.generateJwt_arguments).isEqualTo(givenUserAuthenticationToken);
    }

}
