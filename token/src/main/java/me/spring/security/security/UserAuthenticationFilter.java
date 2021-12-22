package me.spring.security.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserAuthenticationTokenFactory userAuthenticationTokenFactory;

    public UserAuthenticationFilter(AuthenticationManager authenticationManager, UserAuthenticationTokenFactory userAuthenticationTokenFactory) {
        setAuthenticationManager(authenticationManager);
        this.userAuthenticationTokenFactory = userAuthenticationTokenFactory;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        final UserAuthenticationToken unAuthenticatedToken = userAuthenticationTokenFactory.unAuthenticatedToken(request);
        return getAuthenticationManager().authenticate(unAuthenticatedToken);
    }
}
