package com.devteria.identity_.repository;
import com.devteria.identity_.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {}