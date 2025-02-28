package com.test.financialunit.util;


import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@UtilityClass
public class SecurityUtils {


    public User getCurrentAuthenticated() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
