package me.spring.security.config.testdouble;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SpyUserDetailsService implements UserDetailsService {

    public String loadUserByUsername_arguments;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        loadUserByUsername_arguments = username;
        return null;
    }

}
