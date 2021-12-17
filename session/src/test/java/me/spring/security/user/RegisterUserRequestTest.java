package me.spring.security.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterUserRequestTest {

    @Test
    void toEntity() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest(
                "name",
                "password",
                "email");
        UserEntity toEntity_returns = registerUserRequest.toEntity();
        assertThat(toEntity_returns.getName()).isEqualTo("name");
        assertThat(toEntity_returns.getPassword()).isEqualTo("password");
        assertThat(toEntity_returns.getEmail()).isEqualTo("email");
    }
}
