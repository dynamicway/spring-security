package me.spring.security.security;

import me.spring.security.user.UserEntity;
import me.spring.security.user.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserAuthenticationTokenTest {

    @Test
    void applyPrincipal_updateUserAuthorities() {
        UserAuthenticationToken givenUserAuthenticationToken = new UserAuthenticationToken();
        UserEntity givenUserEntity = new UserEntity();
        givenUserEntity.getRoles().addAll(List.of(
                new UserRoleEntity(1L, UserRoleEntity.Role.USER),
                new UserRoleEntity(1L, UserRoleEntity.Role.ADMIN)
        ));
        givenUserAuthenticationToken.applyPrincipal(givenUserEntity);
        assertThat(givenUserAuthenticationToken.getUserAuthorities()).containsExactlyInAnyOrder(
                new SimpleGrantedAuthority("USER"),
                new SimpleGrantedAuthority("ADMIN")
        );
    }

    @Test
    void applyPrincipal_updateUserEntity() {
        UserAuthenticationToken givenUserAuthenticationToken = new UserAuthenticationToken();
        UserEntity givenUserEntity = new UserEntity();
        givenUserAuthenticationToken.applyPrincipal(givenUserEntity);
        assertThat(givenUserAuthenticationToken.getUserEntity()).isEqualTo(givenUserEntity);
    }

}
