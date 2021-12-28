package me.spring.security.security;

import me.spring.security.security.testdouble.SpyJwtTokenManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

class JwtInterceptorTest {

    private JwtInterceptor jwtInterceptor;
    private SpyJwtTokenManager spyJwtTokenManager;

    @BeforeEach
    void setUp() {
        spyJwtTokenManager = new SpyJwtTokenManager();
        jwtInterceptor = new JwtInterceptor(spyJwtTokenManager);
    }

    @Test
    void preHandle_callsValid_inJwtTokenManager_when_authorization_isNotNull() {
        MockHttpServletRequest givenHttpRequest = new MockHttpServletRequest();
        givenHttpRequest.addHeader(HttpHeaders.AUTHORIZATION, "Bearer givenJwt");
        jwtInterceptor.preHandle(givenHttpRequest, new MockHttpServletResponse(), null);
        assertThat(spyJwtTokenManager.valid_arguments).isEqualTo("givenJwt");
    }

    @Test
    void preHandler_returnsFalse_when_authorization_isNull() {
        boolean preHandle_returns = jwtInterceptor.preHandle(new MockHttpServletRequest(), new MockHttpServletResponse(), null);
        assertThat(preHandle_returns).isFalse();
    }

    @Test
    void preHandler_returnsFalse_when_authorization_isNotStartsWithBearer() {
        MockHttpServletRequest givenHttpRequest = new MockHttpServletRequest();
        givenHttpRequest.addHeader(HttpHeaders.AUTHORIZATION, "");
        boolean preHandle_returns = jwtInterceptor.preHandle(givenHttpRequest, new MockHttpServletResponse(), null);
        assertThat(preHandle_returns).isFalse();
    }

}