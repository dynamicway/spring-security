package me.spring.security.config;

import me.spring.security.error.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationTokenProvider extends UsernamePasswordAuthenticationFilter {

    public AuthenticationTokenProvider(AuthenticationManager authenticationManager) {
        setAuthenticationManager(authenticationManager);
        setFilterProcessesUrl("/login");
        setAuthenticationSuccessHandler(new LoginSuccessHandler());
        setAuthenticationFailureHandler(new LoginFailureHandler());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        validateRequest(request);
        UsernamePasswordAuthenticationToken attemptAuthenticationToken = createAttemptAuthenticationToken(request);
        return getAuthenticationManager().authenticate(attemptAuthenticationToken);
    }

    private UsernamePasswordAuthenticationToken createAttemptAuthenticationToken(HttpServletRequest request) {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        return new UsernamePasswordAuthenticationToken(id, password);
    }

    private void validateRequest(HttpServletRequest request) {
        if (request.getParameter("id") == null || request.getParameter("password") == null)
            throw new BadRequestException("not exists id or password");
    }

}
