package me.spring.security.user;

import me.spring.security.error.BadRequestException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RegisterUserRequestTest {

    @Test
    void toEntity() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest(
                "name",
                "password",
                "email");
        registerUserRequest.getRoles().add(UserRole.Role.USER);
        UserEntity toEntity_returns = registerUserRequest.toEntity();
        assertThat(toEntity_returns.getName()).isEqualTo("name");
        assertThat(toEntity_returns.getPassword()).isEqualTo("password");
        assertThat(toEntity_returns.getEmail()).isEqualTo("email");
        assertThat(toEntity_returns.getRoles()).containsExactly(
                new UserRole(toEntity_returns, UserRole.Role.USER)
        );

    }

    @Test
    void toEntity_throwsBadRequestException_when_rolesIsEmpty() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest(
                "name",
                "password",
                "email");

        assertThatThrownBy(registerUserRequest::toEntity)
                .isInstanceOf(BadRequestException.class)
                .hasMessage("roles is empty");
    }
}
