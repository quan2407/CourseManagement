package com.quan.cms.mapper;

import com.quan.cms.dto.request.CreateUserRequest;
import com.quan.cms.dto.response.UserResponse;
import com.quan.cms.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "passwordHash", ignore = true)
    User toEntity(CreateUserRequest request);

    @Mapping(
            target = "role",
            expression = "java(user.getRole().name())"
    )
    UserResponse toResponse(User user);
}