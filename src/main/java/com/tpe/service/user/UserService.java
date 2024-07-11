package com.tpe.service.user;

import com.tpe.entity.enums.RoleType;
import com.tpe.entity.user.User;
import com.tpe.entity.user.Role;
import com.tpe.payload.mapper.UserMapper;
import com.tpe.payload.request.user.SaveUserRequest;
import com.tpe.payload.request.user.UserRequest;
import com.tpe.payload.response.user.UserResponse;
import com.tpe.repository.user.UserRepository;
import com.tpe.repository.user.UserRoleRepository;
import com.tpe.service.helper.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final ValidationUtil validationUtil;

//    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
//        this.userRepository = userRepository;
//        this.userRoleRepository = userRoleRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.userMapper = userMapper;
//    }

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

    public UserResponse saveUser(UserRequest userRequest)
    {
        validationUtil.checkDublicate(userRequest.getEmail(), userRequest.getPassword());

        User member = userMapper.toEntity(userRequest);
        member.setRoles(userRoleService.getUserRole(RoleType.ROLE_EMPLOYEE));
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return userRepository.save(this::saveUser);//todo burda kaldım
    }


//    public User saveUser(UserRequest userRequest) {
//        // Check user already registered or not
//        Optional<User> existingUser = userRepository.findByEmail(userRequest.getEmail());
//        if (existingUser.isPresent()) {
//            throw new IllegalArgumentException("User with this email already exists");
//        }
//
//        // UserRequest'den User entity
//        User user = userMapper.toEntity(userRequest);
//
//        // save user to db
//        return userRepository.save(user);
//    }


}
