package me.spring.security.user;

import me.spring.security.error.BadRequestException;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserServiceTest {

    private UserService userService;
    private SpyUserRepository spyUserRepository;
    private SpyUserRoleRepository spyUserRoleRepository;

    @BeforeEach
    void setUp() {
        spyUserRepository = new SpyUserRepository();
        spyUserRoleRepository = new SpyUserRoleRepository();
        userService = new UserServiceImpl(spyUserRepository, spyUserRoleRepository);
    }

    @Test
    void registerUser_callsSave_inUserRepository() {
        RegisterUserRequest givenRegisterUserRequest = new RegisterUserRequest("name", "password", "email");
        givenRegisterUserRequest.getRoles().add(UserRole.Role.USER);
        userService.registerUser(givenRegisterUserRequest);

        assertThat(spyUserRepository.save_arguments.getName()).isEqualTo("name");
        assertThat(spyUserRepository.save_arguments.getPassword()).isEqualTo("password");
        assertThat(spyUserRepository.save_arguments.getEmail()).isEqualTo("email");
    }

    @Test
    void registerUser_callsSaveAll_inUserRoleRepository() {
        RegisterUserRequest givenRegisterUserRequest = new RegisterUserRequest("name", "password", "email");
        givenRegisterUserRequest.getRoles().add(UserRole.Role.USER);

        userService.registerUser(givenRegisterUserRequest);
        assertThat(spyUserRoleRepository.saveAll_arguments.size()).isEqualTo(1);
        assertThat(spyUserRoleRepository.saveAll_arguments).containsExactly(
                new UserRole(
                        givenRegisterUserRequest.toEntity(),
                        UserRole.Role.USER
                )
        );

    }

    @Test
    void registerUser_throwsBadRequestException_when_userRolesIsEmpty() {
        RegisterUserRequest givenRegisterUserRequest = new RegisterUserRequest("name", "password", "email");

        assertThatThrownBy(() -> userService.registerUser(givenRegisterUserRequest))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("roles is empty");
    }

    @Test
    void getUsers_callsFindAll_inUserRepository() {
        spyUserRepository.findAll_returns = Lists.emptyList();
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
