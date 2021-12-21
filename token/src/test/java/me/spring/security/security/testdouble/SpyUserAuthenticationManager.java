package me.spring.security.security.testdouble;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class SpyUserAuthenticationManager implements AuthenticationManager {

    public Authentication authenticate_arguments;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        authenticate_arguments = authentication;
        return null;
    }

}
