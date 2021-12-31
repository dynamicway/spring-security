package me.spring.security.user;

import lombok.Getter;
import me.spring.security.security.oauth.ResourceServer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.*;

@Entity
@Getter
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nick_name")
    private String nickName;
    private LocalDate birth;

    @Column(name = "resource_server_name")
    @Enumerated(STRING)
    private ResourceServer resourceServer;

    @Column(name = "resource_server_id")
    private long resourceServerId;

    @OneToMany
    private final List<UserRoleEntity> roles = new ArrayList<>();

}
