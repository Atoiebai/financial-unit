package com.test.financialunit.user;

import com.test.financialunit.balance.UserBalanceService;
import com.test.financialunit.user.dto.ChangePasswordRequest;
import com.test.financialunit.user.dto.ChangeStatusRequest;
import com.test.financialunit.user.dto.ChangeStatusResponse;
import com.test.financialunit.user.dto.CreateUserRequest;
import com.test.financialunit.user.dto.CreateUserResponse;
import com.test.financialunit.user.dto.UserDto;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService {

    final UserRepository userRepository;
    final UserMapper userMapper;
    @Lazy @Autowired UserBalanceService userBalanceService;

    @Transactional
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        var user = userMapper.toEntity(createUserRequest);
        userRepository.save(user);
        userBalanceService.createUserBalance(user.getId());
        return userMapper.createdResponse(user);
    }

    public ChangeStatusResponse changeState(ChangeStatusRequest changeStatusRequest) {
        return userRepository.findByUsername(changeStatusRequest.username())
            .map(user -> saveNewState(changeStatusRequest, user))
            .map(userMapper::changedState)
            .orElse(null);
    }


    public List<UserDto> getUsers() {
        return userRepository.findAll()
            .stream()
            .map(userMapper::toDto)
            .collect(Collectors.toUnmodifiableList());
    }

    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        userRepository.findByUsername(changePasswordRequest.username())
            .map(user -> saveWithPassword(changePasswordRequest, user))
            .orElse(null);
    }

    private User saveWithPassword(ChangePasswordRequest changePasswordRequest, User user) {
        user.setPassword(userMapper.transformPassword(changePasswordRequest.newPassword()));
        return userRepository.save(user);
    }

    public User findBy(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }
    private User saveNewState(ChangeStatusRequest changeStatusRequest, User user) {
        user.setState(changeStatusRequest.state());
        return userRepository.save(user);
    }
}
