package com.devteria.identity_.Mapper;

import com.devteria.identity_.dto.request.RoleRequest;
import com.devteria.identity_.dto.response.RoleResponse;
import com.devteria.identity_.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);
}