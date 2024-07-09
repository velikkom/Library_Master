package com.tpe.repository.user;

import com.tpe.entity.enums.RoleType;
import com.tpe.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<Role,Long> {

    @Query("SELECT r FROM Role r WHERE r.roleType = :role")
    Optional<Role> findByEnumRole(@Param("role") RoleType roleType);

    Optional<Role> findByRoleType(RoleType roleType);



}
