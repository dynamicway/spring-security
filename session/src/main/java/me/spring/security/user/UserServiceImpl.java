package me.spring.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void registerUser(RegisterUserRequest registerUserRequest) {
        userRepository.save(registerUserRequest.toEntity());
    }
}
