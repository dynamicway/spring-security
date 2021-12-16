package me.spring.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void registerUser(RegisterUserRequest registerUserRequest) {
        userRepository.save(registerUserRequest.toEntity());
    }

    @Override
    public List<GetUser> getUsers() {
        return null;
    }
}
