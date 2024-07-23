package com.devteria.identity_.service;

import com.devteria.identity_.Mapper.PermissionMapper;
import com.devteria.identity_.dto.request.PermissionRequest;
import com.devteria.identity_.dto.response.PermissionResponse;
import com.devteria.identity_.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.devteria.identity_.entity.Permission;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)

    public class PermissionService {
        PermissionRepository permissionRepository;
        PermissionMapper permissionMapper;

        public PermissionResponse create(PermissionRequest request) {
            Permission permission = permissionMapper.toPermission(request);
            permission = permissionRepository.save(permission);
            return permissionMapper.toPermissionResponse(permission);
        }

        public List<PermissionResponse> getAll() {
            var permissions = permissionRepository.findAll();
            return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
        }

        public void delete(String permission) {
            permissionRepository.deleteById(permission);
        }
    }

