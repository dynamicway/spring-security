package me.spring.security.user;

import java.util.List;

public interface UserService {

    List<UserEntity> getAllUsers();

    UserEntity getUser(long userId);

}
