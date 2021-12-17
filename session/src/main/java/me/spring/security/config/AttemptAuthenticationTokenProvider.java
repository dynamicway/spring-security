package me.spring.security.config;

import me.spring.security.error.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AttemptAuthenticationTokenProvider extends UsernamePasswordAuthenticationFilter {

    public AttemptAuthenticationTokenProvider(AuthenticationManager authenticationManager) {
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        return createAuthenticationToken(request);
    }

    private UsernamePasswordAuthenticationToken createAuthenticationToken(HttpServletRequest request) {
        validateRequest(request);
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        return new UsernamePasswordAuthenticationToken(id, password);
    }

    private void validateRequest(HttpServletRequest request) {
        if (request.getParameter("id") == null || request.getParameter("password") == null)
            throw new BadRequestException("not exists id or password");
    }

}