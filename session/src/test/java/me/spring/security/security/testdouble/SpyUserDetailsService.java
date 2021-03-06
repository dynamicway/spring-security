package me.spring.security.security.testdouble;

import me.spring.security.security.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SpyUserDetailsService implements UserDetailsService {

    public String loadUserByUsername_arguments;
    public UserDetailsImpl loadUserByUsername_returns;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        loadUserByUsername_arguments = username;
        return loadUserByUsername_returns;
    }

}
