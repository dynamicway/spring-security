package me.spring.security.security.testdouble;

import me.spring.security.security.UserAuthenticationManager;
import me.spring.security.security.UserAuthenticationToken;

public class SpyUserAuthenticationManager implements UserAuthenticationManager {

    public UserAuthenticationToken authenticate_arguments;
    public UserAuthenticationToken authenticate_returns;

    @Override
    public UserAuthenticationToken authenticate(UserAuthenticationToken authenticationToken) {
        authenticate_arguments = authenticationToken;
        return authenticate_returns;
    }

}
