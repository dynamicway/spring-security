package me.spring.security.config;

import me.spring.security.user.SpyUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserDetailsServiceTest {

    private SpyUserRepository spyUserRepository;
    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    void setUp() {
        spyUserRepository = new SpyUserRepository();
        userDetailsService = new UserDetailsServiceImpl(
                spyUserRepository
        );
    }

    @Test
    void loadUserByUsername_callsFindByName_inUserRepository() {
        String givenUsername = "userName";
        userDetailsService.loadUserByUsername(givenUsername);
        assertThat(spyUserRepository.findByName_arguments).isEqualTo(givenUsername);
    }
}
