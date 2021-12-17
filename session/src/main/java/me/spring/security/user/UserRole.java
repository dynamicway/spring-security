package me.spring.security.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = LAZY)
    private UserEntity user;

    @Enumerated(STRING)
    private Role role;

    public enum Role {
        USER, ADMIN
    }

    public UserRole(UserEntity userEntity, Role role) {
        this.user = userEntity;
        this.role = role;
    }
}
