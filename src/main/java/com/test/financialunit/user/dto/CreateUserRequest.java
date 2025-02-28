package com.test.financialunit.user.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;


public record CreateUserRequest(

        @NotNull String password,

        @NotNull String username,

        @Enumerated(EnumType.STRING)
        @NotNull UserState state

) {
}
