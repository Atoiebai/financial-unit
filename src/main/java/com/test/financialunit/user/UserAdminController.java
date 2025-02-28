package com.test.financialunit.user;

import com.test.financialunit.user.dto.ChangePasswordRequest;
import com.test.financialunit.user.dto.ChangeStatusRequest;
import com.test.financialunit.user.dto.ChangeStatusResponse;
import com.test.financialunit.user.dto.CreateUserRequest;
import com.test.financialunit.user.dto.CreateUserResponse;
import com.test.financialunit.user.dto.UserDto;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true , level = AccessLevel.PRIVATE)
 class UserAdminController {

    UserService userService;

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        return new ResponseEntity<>(userService.createUser(createUserRequest),HttpStatus.CREATED);
    }

    @PutMapping("/change-state")
    public ResponseEntity<ChangeStatusResponse> changeState(@RequestBody @Valid ChangeStatusRequest changeStatusRequest) {
        var changes = userService.changeState(changeStatusRequest);
        if(changes == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(changes);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PutMapping("/change-password")
    public ResponseEntity<HttpStatus> changePassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest) {
        userService.changePassword(changePasswordRequest);
        return ResponseEntity.ok().build();
    }

}
