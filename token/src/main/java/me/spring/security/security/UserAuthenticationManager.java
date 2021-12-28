package me.spring.security.security;

import org.springframework.security.authentication.AuthenticationManager;

public interface UserAuthenticationManager extends AuthenticationManager {

    UserAuthenticationToken authenticate(UserAuthenticationToken authenticationToken);

}
