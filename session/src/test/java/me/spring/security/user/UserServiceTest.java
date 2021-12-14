package me.spring.security.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class UserServiceTest {

    private UserService userService;
    private SpyUserRepository spyUserRepository;

    @BeforeEach
    void setUp() {
        spyUserRepository = new SpyUserRepository();
        userService = new UserServiceImpl(spyUserRepository);
    }

    @Test
    void registerUser_callsSave_inUserRepository() {
        RegisterUserRequest givenRegisterUserRequest = new RegisterUserRequest("name", "password", "email");
        userService.registerUser(givenRegisterUserRequest);

        assertSoftly(s -> {
            s.assertThat(spyUserRepository.save_arguments.getName()).isEqualTo("name");
            s.assertThat(spyUserRepository.save_arguments.getPassword()).isEqualTo("password");
            s.assertThat(spyUserRepository.save_arguments.getEmail()).isEqualTo("email");
        });
    }
}
