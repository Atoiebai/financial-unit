package com.test.financialunit.user;

import com.test.financialunit.user.dto.UserState;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
 class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findByUsername(username)
            .orElse(null);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        if (!user.getState().equals(UserState.ACTIVE)) {
            throw new UsernameNotFoundException("User with username: " + username + " is not ACTIVE. State: " + user.getState());
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
