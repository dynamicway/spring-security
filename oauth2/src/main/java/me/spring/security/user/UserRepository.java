package me.spring.security.user;

import me.spring.security.security.oauth.ResourceServer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByResourceServerAndResourceServerId(ResourceServer resourceServer, String resourceServerId);

}
