package me.spring.security.security.testdouble;

import me.spring.security.security.JwtTokenManager;
import me.spring.security.security.UserAuthenticationToken;

public class SpyJwtTokenManager implements JwtTokenManager {

    public UserAuthenticationToken generateJwt_arguments;
    public String generateJwt_returns;

    @Override
    public String generateJwt(UserAuthenticationToken authentication) {
        generateJwt_arguments = authentication;
        return generateJwt_returns;
    }

    @Override
    public void valid(String token) {

    }

}
