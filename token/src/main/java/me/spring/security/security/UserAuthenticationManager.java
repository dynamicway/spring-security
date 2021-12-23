package me.spring.security.security;

public interface UserAuthenticationManager {

    UserAuthenticationToken authenticate(UserAuthenticationToken authenticationToken);

}
