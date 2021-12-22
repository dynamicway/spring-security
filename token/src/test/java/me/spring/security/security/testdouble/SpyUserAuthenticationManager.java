package me.spring.security.security.testdouble;

import me.spring.security.security.UserAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class SpyUserAuthenticationManager implements AuthenticationManager {

    public Authentication authenticate_arguments;
    public UserAuthenticationToken authenticate_returns;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        authenticate_arguments = authentication;
        return authenticate_returns;
    }

}
