package me.spring.security.security.testdouble;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SpyBCryptPasswordEncoder extends BCryptPasswordEncoder {

    public boolean matches_isCalled;
    public boolean matches_returns;

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        matches_isCalled = true;
        return matches_returns;
    }

}
