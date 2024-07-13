package com.tpe.repository.user;

import com.tpe.entity.enums.RoleType;
import com.tpe.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Object> findByRoleName(RoleType roleName);
}
