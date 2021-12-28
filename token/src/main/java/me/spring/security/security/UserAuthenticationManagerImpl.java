package me.spring.security.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAuthenticationManagerImpl implements UserAuthenticationManager {

    private final UserAuthenticationProvider userAuthenticationProvider;

    public UserAuthenticationToken authenticate(UserAuthenticationToken authenticationToken) {
        return userAuthenticationProvider.authenticate(authenticationToken);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }
}
