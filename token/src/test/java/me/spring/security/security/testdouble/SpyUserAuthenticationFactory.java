package me.spring.security.security.testdouble;

import me.spring.security.security.UserAuthenticationToken;
import me.spring.security.security.UserAuthenticationTokenFactory;

import javax.servlet.http.HttpServletRequest;

public class SpyUserAuthenticationFactory implements UserAuthenticationTokenFactory {

    public HttpServletRequest unAuthenticatedToken_arguments;
    public UserAuthenticationToken unAuthenticatedToken_returns;

    @Override
    public UserAuthenticationToken unAuthenticatedToken(HttpServletRequest httpServletRequest) {
        unAuthenticatedToken_arguments = httpServletRequest;
        return unAuthenticatedToken_returns;
    }

}
