package me.spring.security.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.*;

@Entity(name = "USER_ROLE")
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class UserRoleEntity {

    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(name = "user_id")
    private long userId;

    @Enumerated(STRING)
    private Role role;

    public UserRoleEntity(Role role) {
        this.role = role;
    }

    public enum Role {
        USER,
        ADMIN
    }

}
