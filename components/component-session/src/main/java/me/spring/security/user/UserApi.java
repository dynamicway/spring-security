package me.spring.security.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class UserApi {

    @GetMapping("/users")
    @ResponseStatus(CREATED)
    public void registerUser() {

    }
}
