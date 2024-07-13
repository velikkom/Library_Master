package com.tpe.service.user;

import com.tpe.entity.enums.RoleType;
import com.tpe.entity.user.Role;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.payload.messages.ErrorMessages;
import com.tpe.repository.user.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public Role getUserRole(RoleType roleType){

        return  userRoleRepository.findByEnumRole(roleType).orElseThrow(()->
                new ResourceNotFoundException(ErrorMessages.ROLE_NOT_FOUND));

    }

    public List<Role> getAllUserRole() {
        return userRoleRepository.findAll();
    }
}
