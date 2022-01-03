package me.spring.security.security.oauth;

import lombok.RequiredArgsConstructor;
import me.spring.security.security.jwt.Jwt;
import me.spring.security.security.jwt.JwtManager;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtManager jwtManager;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Jwt jwt = jwtManager.generateJwt(oAuth2User);
        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + jwt.getAccessToken());
        response.addHeader("Refresh-Token", "Bearer " + jwt.getRefreshToken());
    }

}
