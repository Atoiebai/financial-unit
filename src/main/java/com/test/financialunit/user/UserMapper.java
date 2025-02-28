package com.test.financialunit.user;

import com.test.financialunit.user.dto.ChangeStatusResponse;
import com.test.financialunit.user.dto.CreateUserRequest;
import com.test.financialunit.user.dto.CreateUserResponse;
import com.test.financialunit.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
 abstract class UserMapper {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Mapping(expression =  "java(java.util.UUID.randomUUID())", target = "id")
    @Mapping(expression = "java(java.sql.Timestamp.from(java.time.Instant.now()))", target = "createdAt")
    @Mapping(expression = "java(java.sql.Timestamp.from(java.time.Instant.now()))", target = "updatedAt")
    @Mapping(expression = "java(com.test.financialunit.user.dto.UserRole.BASE)", target = "role")
    @Mapping(source = "state", target = "state")
    @Mapping(source = "password", target = "password", qualifiedByName = "transformPassword")
    public abstract User toEntity(CreateUserRequest createUserRequest);

    public abstract CreateUserResponse createdResponse(User user);


    public abstract UserDto toDto(User user);

    public abstract ChangeStatusResponse changedState(User user);

    @Named("transformPassword")
    public String transformPassword(String password) {
       return passwordEncoder.encode(password);
    }
}
