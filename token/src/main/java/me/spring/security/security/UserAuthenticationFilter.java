package me.spring.security.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserAuthenticationTokenFactory userAuthenticationTokenFactory;
    private final UserAuthenticationManager userAuthenticationManagerImpl;

    public UserAuthenticationFilter(UserAuthenticationTokenFactory userAuthenticationTokenFactory, UserAuthenticationManager authenticationManager, AuthenticationSuccessHandler userAuthenticationSuccessHandler) {
        this.userAuthenticationTokenFactory = userAuthenticationTokenFactory;
        this.userAuthenticationManagerImpl = authenticationManager;
        setAuthenticationManager(authenticationManager);
        setFilterProcessesUrl("/login");
        setAuthenticationSuccessHandler(userAuthenticationSuccessHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        final UserAuthenticationToken unAuthenticatedToken = userAuthenticationTokenFactory.unAuthenticatedToken(request);
        return userAuthenticationManagerImpl.authenticate(unAuthenticatedToken);
    }

}
