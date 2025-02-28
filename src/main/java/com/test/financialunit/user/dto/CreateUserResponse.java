package com.test.financialunit.user.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.UUID;


public record CreateUserResponse(
    UUID id,

    String username,

    @Enumerated(EnumType.STRING)
    UserState state,

    @Enumerated(EnumType.STRING)
    UserRole role
) {
}
