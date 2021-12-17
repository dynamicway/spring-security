package me.spring.security.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GetUserTest {

    @Test
    void constructor_argumentsEntity() {
        UserEntity givenUserEntity = new UserEntity(
                1L,
                "name",
                "password",
                "email"
        );
        GetUser constructor_returns = new GetUser(givenUserEntity);
        assertThat(constructor_returns.getId()).isEqualTo(1L);
        assertThat(constructor_returns.getName()).isEqualTo("name");
        assertThat(constructor_returns.getEmail()).isEqualTo("email");
    }
}
