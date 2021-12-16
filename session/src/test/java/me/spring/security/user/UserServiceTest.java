package me.spring.security.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    void getUsers_callsFindAll_inUserRepository() {
        userService.getUsers();
        assertThat(spyUserRepository.isCalled_findAll).isTrue();
    }

    @Test
    void getUsers_returnsGetUsers() {
        spyUserRepository.findAll_returns = List.of(
                new UserEntity(
                        1L,
                        "name1",
                        "password",
                        "email1"
                ),
                new UserEntity(
                        2L,
                        "name2",
                        "password",
                        "email2"
                ),
                new UserEntity(
                        3L,
                        "name3",
                        "password",
                        "email3"
                ),
                new UserEntity(
                        4L,
                        "name4",
                        "password",
                        "email4"
                )
        );

        List<GetUser> actualUsers = userService.getUsers();
        assertThat(actualUsers).containsExactly(
                new GetUser(
                        1L,
                        "name1",
                        "email1"
                ),
                new GetUser(
                        2L,
                        "name2",
                        "email2"
                ),
                new GetUser(
                        3L,
                        "name3",
                        "email3"
                ),
                new GetUser(
                        4L,
                        "name4",
                        "email4"
                )
        );
    }
}
