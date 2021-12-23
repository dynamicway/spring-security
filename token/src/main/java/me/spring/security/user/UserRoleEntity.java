package me.spring.security.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
@NoArgsConstructor
public class UserRoleEntity {
    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(name = "user_id")
    private long userId;

    @Enumerated(STRING)
    private Role role;

    public UserRoleEntity(long userId, Role role) {
        this.userId = userId;
        this.role = role;
    }

    public SimpleGrantedAuthority toSimpleGrantedAuthority() {
        return new SimpleGrantedAuthority(role.name());
    }

    public enum Role {
        USER, ADMIN
    }

}
