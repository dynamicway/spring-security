package me.spring.security.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.spring.security.user.UserEntity;
import me.spring.security.user.UserRoleEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
public class UserAuthenticationToken implements Authentication {
    private final Set<SimpleGrantedAuthority> userAuthorities = new HashSet<>();
    private String email;
    private String password;
    private UserEntity userEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return !userAuthorities.isEmpty();
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }

    public void applyPrincipal(UserEntity userEntity) {
        this.userEntity = userEntity;
        userEntity.getRoles().stream()
                .map(UserRoleEntity::toSimpleGrantedAuthority)
                .forEach(userAuthorities::add);
    }

}
