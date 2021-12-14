package me.spring.security.user;

import lombok.*;

import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor
@EqualsAndHashCode
public class RegisterUserRequest {
    private String name;
    private String password;
    private String email;

    public UserEntity toEntity() {
        return new UserEntity(
                name,
                password,
                email
        );
    }
}
