package com.test.financialunit.user.dto;


import jakarta.validation.constraints.NotNull;

public record ChangePasswordRequest(
    @NotNull String newPassword,
    @NotNull String username
) {
}
