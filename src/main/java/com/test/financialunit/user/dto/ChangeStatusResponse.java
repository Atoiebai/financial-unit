package com.test.financialunit.user.dto;


import jakarta.validation.constraints.NotNull;

public record ChangeStatusResponse(
    @NotNull String username,
    @NotNull UserState state
) {
}
