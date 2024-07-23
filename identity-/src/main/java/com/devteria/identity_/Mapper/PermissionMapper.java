package com.devteria.identity_.Mapper;

import com.devteria.identity_.dto.request.PermissionRequest;
import com.devteria.identity_.dto.response.PermissionResponse;
import com.devteria.identity_.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}