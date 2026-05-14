package com.quan.cms.mapper;

import com.quan.cms.dto.request.CreateUserRequest;
import com.quan.cms.dto.request.UpdateProfileRequest;
import com.quan.cms.dto.response.UserResponse;
import com.quan.cms.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "passwordHash", ignore = true)
    User toEntity(CreateUserRequest request);

    @Mapping(
            target = "role",
            expression = "java(user.getRole().name())"
    )
    UserResponse toResponse(User user);
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    void updateProfileFromRequest(
            UpdateProfileRequest request,
            @MappingTarget User user
    );
}