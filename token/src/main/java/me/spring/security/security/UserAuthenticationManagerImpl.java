package me.spring.security.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAuthenticationManagerImpl implements UserAuthenticationManager {

    private final UserAuthenticationProvider userAuthenticationProvider;

    @Override
    public UserAuthenticationToken authenticate(UserAuthenticationToken authenticationToken) {
        return null;
    }

}
