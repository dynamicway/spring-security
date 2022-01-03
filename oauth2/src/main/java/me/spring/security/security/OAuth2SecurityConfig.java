package me.spring.security.security;

import lombok.RequiredArgsConstructor;
import me.spring.security.security.oauth.CustomOAuth2UserService;
import me.spring.security.security.oauth.OAuth2LoginSuccessHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@RequiredArgsConstructor
public class OAuth2SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin()
                .disable()
                .oauth2Login()
                .successHandler(oAuth2LoginSuccessHandler)
                .userInfoEndpoint().userService(customOAuth2UserService);
    }

}
