package me.spring.security.security.testdouble;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class SpyAuthenticationManager implements AuthenticationManager {

    public UsernamePasswordAuthenticationToken authenticate_returns;
    public Authentication authenticate_argumentsAuthentication;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        authenticate_argumentsAuthentication = authentication;
        return authenticate_returns;
    }

}
