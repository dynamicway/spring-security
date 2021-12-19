package me.spring.security.user;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
        return new UserEntity(
                name,
                password,
                email
        );
    }
}
