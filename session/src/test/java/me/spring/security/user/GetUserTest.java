package me.spring.security.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

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
        assertSoftly(s -> {
            s.assertThat(constructor_returns.getId()).isEqualTo(1L);
            s.assertThat(constructor_returns.getName()).isEqualTo("name");
            s.assertThat(constructor_returns.getEmail()).isEqualTo("email");
        });
    }
}
