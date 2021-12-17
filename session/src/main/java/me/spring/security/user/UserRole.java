package me.spring.security.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.*;

@Entity
@Getter
@NoArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_id")
    private long userId;

    @Enumerated(STRING)
    private Role role;

    public enum Role {
        USER, ADMIN
    }

    public UserRole(long userId, Role role) {
        this.userId = userId;
        this.role = role;
    }
}
