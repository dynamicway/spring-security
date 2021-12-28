package me.spring.security.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenManager jwtTokenManager;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final UserAuthenticationToken userAuthenticationToken = (UserAuthenticationToken) authentication;
        final String jwt = jwtTokenManager.generateJwt(userAuthenticationToken);
        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);
    }

}
