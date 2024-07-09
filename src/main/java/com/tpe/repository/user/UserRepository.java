package com.tpe.repository.user;

import com.tpe.entity.enums.RoleType;
import com.tpe.entity.user.Role;
import com.tpe.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);


    @Query("SELECT COUNT(u) FROM User u JOIN u.roles r WHERE r.roleType = 'ADMIN'")
    int countAllAdmins();

    long countByRoles(Role role);

}
