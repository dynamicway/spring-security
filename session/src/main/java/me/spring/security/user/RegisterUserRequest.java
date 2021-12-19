package me.spring.security.user;

import lombok.*;
import me.spring.security.error.BadRequestException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor
@EqualsAndHashCode
public class RegisterUserRequest {
    private String name;
    private String password;
    private String email;
    private final List<UserRole.Role> roles = new ArrayList<>();

    public UserEntity toEntity() {
        validate();
        UserEntity userEntity = new UserEntity(
                name,
                password,
                email
        );
        Set<UserRole> userRoles = roles.stream()
                .map(role -> new UserRole(userEntity, role))
                .collect(Collectors.toSet());
        userEntity.getRoles().addAll(userRoles);
        return userEntity;
    }

    private void validate() {
        if (roles.isEmpty())
            throw new BadRequestException("roles is empty");
    }
}
