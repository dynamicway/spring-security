package me.spring.security.security;

public interface JwtTokenManager {

    String generateJwt(UserAuthenticationToken authentication);

    void valid(String token);

}
