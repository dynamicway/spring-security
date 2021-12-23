package me.spring.security.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static org.assertj.core.api.Assertions.assertThat;

class UserRoleEntityTest {

    @Test
    void constructor() {
        UserRoleEntity givenUserRoleEntity = new UserRoleEntity(1L, UserRoleEntity.Role.USER);
        assertThat(givenUserRoleEntity.getUserId()).isEqualTo(1L);
        assertThat(givenUserRoleEntity.getRole()).isEqualTo(UserRoleEntity.Role.USER);
    }

    @Test
    void toSimpleGrantedAuthority() {
        UserRoleEntity givenUserRoleEntity = new UserRoleEntity(1L, UserRoleEntity.Role.USER);
        SimpleGrantedAuthority toSimpleGrantedAuthority_expected = new SimpleGrantedAuthority("USER");
        SimpleGrantedAuthority toSimpleGrantedAuthority_returns = givenUserRoleEntity.toSimpleGrantedAuthority();
        assertThat(toSimpleGrantedAuthority_returns).isEqualTo(toSimpleGrantedAuthority_expected);
    }
}
