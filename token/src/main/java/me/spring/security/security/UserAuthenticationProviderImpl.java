package me.spring.security.security;

import lombok.RequiredArgsConstructor;
import me.spring.security.error.BadCredentialException;
import me.spring.security.error.NotFoundEntityException;
import me.spring.security.user.UserEntity;
import me.spring.security.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAuthenticationProviderImpl implements UserAuthenticationProvider {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserAuthenticationToken authenticate(UserAuthenticationToken authenticationToken) {
        UserEntity userEntity = userRepository.findByEmail(authenticationToken.getEmail()).orElseThrow(() -> new NotFoundEntityException("not found user"));
        validateUserEntity(authenticationToken, userEntity);
        authenticationToken.applyPrincipal(userEntity);
        return authenticationToken;
    }

    private void validateUserEntity(UserAuthenticationToken authenticationToken, UserEntity userEntity) {
        boolean isValidPassword = bCryptPasswordEncoder.matches(authenticationToken.getPassword(), userEntity.getPassword());
        if (!isValidPassword) throw new BadCredentialException("invalid password");
    }

}
