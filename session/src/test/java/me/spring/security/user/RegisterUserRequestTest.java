package me.spring.security.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class RegisterUserRequestTest {

    @Test
    void toEntity() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest(
                "name",
                "password",
                "email");
        UserEntity toEntity_returns = registerUserRequest.toEntity();
        assertSoftly(s -> {
            s.assertThat(toEntity_returns.getName()).isEqualTo("name");
            s.assertThat(toEntity_returns.getPassword()).isEqualTo("password");
            s.assertThat(toEntity_returns.getEmail()).isEqualTo("email");
        });
    }
}