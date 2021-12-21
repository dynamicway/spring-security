package me.spring.security.security;

import me.spring.security.error.HasNotUserRoleException;
import me.spring.security.user.UserEntity;
import me.spring.security.user.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserDetailsTest {

    @Test
    void constructor_argumentsUserEntity() {
        UserEntity givenUserEntity = new UserEntity(
                1L,
                "name",
                "password",
                "email"
        );
        givenUserEntity.getRoles().addAll(
                List.of(
                        new UserRole(
                                givenUserEntity,
                                UserRole.Role.USER
                        ),
                        new UserRole(
                                givenUserEntity,
                                UserRole.Role.ADMIN
                        )
                )
        );

        UserDetailsImpl constructor_returns = new UserDetailsImpl(givenUserEntity);
        assertThat(constructor_returns.getUsername()).isEqualTo(givenUserEntity.getName());
        assertThat(constructor_returns.getPassword()).isEqualTo(givenUserEntity.getPassword());
        assertThat(constructor_returns.getAuthorities().contains(new SimpleGrantedAuthority("USER"))).isTrue();
        assertThat(constructor_returns.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))).isTrue();
    }

    @Test
    void constructor_throwsHasNotUserRoleException_when_rolesIsEmpty() {
        UserEntity givenUserEntity = new UserEntity(
                1L,
                "name",
                "password",
                "email"
        );
        assertThatThrownBy(() -> new UserDetailsImpl(givenUserEntity))
                .isInstanceOf(HasNotUserRoleException.class)
                .hasMessage("user role is empty");
    }
}
