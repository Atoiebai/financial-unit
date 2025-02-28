package com.test.financialunit.user.dto;

import java.util.UUID;


public record UserDto(

    UUID id,

    String username,

    String state,

    String role

    ) {
}
