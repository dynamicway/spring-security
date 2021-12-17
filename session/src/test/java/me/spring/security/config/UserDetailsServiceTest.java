package me.spring.security.config;

import me.spring.security.error.NotFoundEntityException;
import me.spring.security.user.SpyUserRepository;
import me.spring.security.user.UserEntity;
import me.spring.security.user.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        UserEntity givenUserEntity = new UserEntity();
        givenUserEntity.getRoles().add(new UserRole(
                givenUserEntity,
                UserRole.Role.USER
        ));
        spyUserRepository.findByName_returns = Optional.of(givenUserEntity);
        userDetailsService.loadUserByUsername(givenUsername);
        assertThat(spyUserRepository.findByName_arguments).isEqualTo(givenUsername);
    }

    @Test
    void loadUserByUsername_throwsNotFoundEntityException_when_notFoundUser() {
        String givenNotExistsUserName = "userName";
        spyUserRepository.findByName_returns = Optional.empty();
        assertThatThrownBy(() -> userDetailsService.loadUserByUsername(givenNotExistsUserName))
                .isInstanceOf(NotFoundEntityException.class)
                .hasMessage("not exists user");
    }

}
