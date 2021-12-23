package me.spring.security.security;

import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProviderImpl implements UserAuthenticationProvider {

    @Override
    public UserAuthenticationToken authenticate(UserAuthenticationToken authenticationToken) {
        return null;
    }
}
