package me.spring.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        List<UserEntity> allUserEntities = userRepository.findAll();
        return allUserEntities.stream()
                .map(GetUser::new)
                .collect(Collectors.toList());
    }
}
