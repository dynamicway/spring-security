package me.spring.security.security.testdouble;

import me.spring.security.security.UserAuthenticationProvider;
import me.spring.security.security.UserAuthenticationToken;

public class SpyUserAuthenticationProvider implements UserAuthenticationProvider {
    public UserAuthenticationToken authenticate_arguments;
    public UserAuthenticationToken authenticate_returns;

    @Override
    public UserAuthenticationToken authenticate(UserAuthenticationToken authenticationToken) {
        authenticate_arguments = authenticationToken;
        return authenticate_returns;
    }
}
