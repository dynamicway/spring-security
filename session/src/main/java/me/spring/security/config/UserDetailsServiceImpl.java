package me.spring.security.config;

import lombok.RequiredArgsConstructor;
import me.spring.security.error.NotFoundEntityException;
import me.spring.security.user.UserEntity;
import me.spring.security.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByName(username)
                .orElseThrow(() -> new NotFoundEntityException("not exists user"));
        return new UserDetailsImpl(userEntity);
    }

}
