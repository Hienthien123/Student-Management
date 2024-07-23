package com.devteria.identity_.repository;

import com.devteria.identity_.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, String> {


}
