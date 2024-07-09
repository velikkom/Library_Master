package com.tpe.service.user;

import com.tpe.entity.enums.RoleType;
import com.tpe.entity.user.User;
import com.tpe.entity.user.Role;
import com.tpe.payload.request.user.SaveUserRequest;
import com.tpe.repository.user.UserRepository;
import com.tpe.repository.user.UserRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public long countAllAdmins() {
        Role adminRole = userRoleRepository.findByRoleType(RoleType.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Admin role not found"));
        return userRepository.countByRoles(adminRole);
    }

    public void saveAdmin(SaveUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setAddress(request.getAddress());
        user.setPhone(request.getPhone());
        user.setBirthDate(request.getBirthDate());
        user.setCreateDate(LocalDateTime.now());
        user.setBuiltIn(true);

        Role adminRole = userRoleRepository.findByRoleType(RoleType.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Admin role not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        user.setRoles(roles);

        userRepository.save(user);
    }


}
