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

    public Set<Role> getUserRole(RoleType roleType){

        return userRoleRepository.findByEnumRole(roleType).orElseThrow(()->
                new ResourceNotFoundException(ErrorMessages.ROLE_NOT_FOUND));

    }

//    public RoleType getRoleFromString (String role){
//
//        if (role.equalsIgnoreCase(RoleType.ADMIN.getName())){
//            return RoleType.ADMIN;
//
//        } else if (role.equalsIgnoreCase(RoleType.MEMBER.getName())){
//            return RoleType.MEMBER;
//
//        }else if (role.equalsIgnoreCase(RoleType.EMPLOYEE.getName())){
//            return RoleType.EMPLOYEE;
//
//        }else throw new BadRequestException(String.format(ErrorMessages.ROLE_DOES_NOT_EXIST,role));
//    }


    public List<Role> getAllUserRole() {
        return userRoleRepository.findAll();
    }
}
