package me.spring.security.security;

import javax.servlet.http.HttpServletRequest;

public interface UserAuthenticationTokenFactory {

    UserAuthenticationToken of(HttpServletRequest httpServletRequest);

}
