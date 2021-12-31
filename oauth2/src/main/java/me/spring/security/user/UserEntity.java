package me.spring.security.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.spring.security.security.oauth.ResourceServer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.EnumType.*;

@Entity(name = "USER")
@Getter
@NoArgsConstructor
@EqualsAndHashCode
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
    private String resourceServerId;

    private String thumbnail;

    @OneToMany(cascade = PERSIST)
    private final List<UserRoleEntity> roles = new ArrayList<>();

    public UserEntity(ResourceServer resourceServer, String resourceServerId, String thumbnail, List<UserRoleEntity> roles) {
        this.resourceServer = resourceServer;
        this.resourceServerId = resourceServerId;
        this.thumbnail = thumbnail;
        this.roles.addAll(roles);
    }
}
