package me.spring.security.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String password;
    private LocalDate birth;

    @OneToMany
    private final List<UserRoleEntity> roles = new ArrayList<>();

}
