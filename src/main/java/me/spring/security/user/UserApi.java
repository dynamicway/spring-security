package me.spring.security.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class UserApi {

    @PostMapping("/users")
    @ResponseStatus(CREATED)
    public void registerUser() {

    }
}
