package com.devteria.identity_.Mapper;
import com.devteria.identity_.dto.request.UserCreatetionRequest;
import com.devteria.identity_.dto.request.UserUpdateRequest;
import com.devteria.identity_.dto.response.UserResponse;
import com.devteria.identity_.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreatetionRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
