package me.spring.security.security;

import javax.servlet.http.HttpServletRequest;

public interface UserAuthenticationTokenFactory {

    UserAuthenticationToken unAuthenticatedToken(HttpServletRequest httpServletRequest);

}
