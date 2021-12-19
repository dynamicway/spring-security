package me.spring.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public void registerUser(RegisterUserRequest registerUserRequest) {
        UserEntity userEntity = registerUserRequest.toEntity();
        userRepository.save(userEntity);
        userRoleRepository.saveAll(userEntity.getRoles());
    }

    @Override
    public List<GetUser> getUsers() {
        List<UserEntity> allUserEntities = userRepository.findAll();
        return allUserEntities.stream()
                .map(GetUser::new)
                .collect(Collectors.toList());
    }
}
