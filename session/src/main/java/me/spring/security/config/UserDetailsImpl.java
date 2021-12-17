package me.spring.security.config;

import lombok.EqualsAndHashCode;
import me.spring.security.error.HasNotUserRoleException;
import me.spring.security.user.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(of = "userEntity")
public class UserDetailsImpl implements UserDetails {

    private final UserEntity userEntity;
    private final List<GrantedAuthority> authorities = new ArrayList<>();

    public UserDetailsImpl(UserEntity userEntity) {
        validateUserEntity(userEntity);
        this.userEntity = userEntity;
        List<SimpleGrantedAuthority> roles = userEntity.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority(r.getRole().name()))
                .collect(Collectors.toList());
        authorities.addAll(roles);
    }

    private void validateUserEntity(UserEntity userEntity) {
        if (userEntity.getRoles().isEmpty())
            throw new HasNotUserRoleException("user role is empty");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
