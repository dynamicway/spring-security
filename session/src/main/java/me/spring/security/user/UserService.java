package me.spring.security.user;

import java.util.List;

public interface UserService {
    void registerUser(RegisterUserRequest registerUserRequest);

    List<GetUser> getUsers();
}
