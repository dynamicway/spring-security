package me.spring.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/users")
    public List<GetUser> getUsers() {
        return userService.getUsers();
    }
}
