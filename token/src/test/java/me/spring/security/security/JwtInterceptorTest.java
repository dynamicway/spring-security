package me.spring.security.security;

import org.junit.jupiter.api.BeforeEach;

class JwtInterceptorTest {

    private JwtInterceptor jwtInterceptor;

    @BeforeEach
    void setUp() {
        jwtInterceptor = new JwtInterceptor();
    }

}