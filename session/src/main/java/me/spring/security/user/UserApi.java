package me.spring.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @PostMapping("/users")
    @ResponseStatus(CREATED)
    public void registerUser(
            @RequestBody RegisterUserRequest registerUserRequest
    ) {
        userService.registerUser(registerUserRequest);
    }
}
