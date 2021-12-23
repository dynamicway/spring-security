package me.spring.security.security;

public interface UserAuthenticationProvider {

    UserAuthenticationToken authenticate(UserAuthenticationToken authenticationToken);

}
