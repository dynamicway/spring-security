package me.spring.security.user;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.EnumType.*;

@Entity
@Getter
public class UserRoleEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "user_id")
    private long userId;
    @Enumerated(STRING)
    private Role role;

    public enum Role {
        USER,
        ADMIN
    }

}
