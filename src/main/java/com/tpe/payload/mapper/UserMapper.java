package com.tpe.payload.mapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tpe.entity.user.User;
import com.tpe.payload.request.user.UserRequest;
import com.tpe.payload.response.user.UserResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserMapper {

    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());

        return response;
    }

    public User toEntity(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setScore(userRequest.getScore());
        user.setAddress(userRequest.getAddress());
        user.setPhone(userRequest.getPhone());
        user.setBirthDate(userRequest.getBirthDate());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setCreateDate(userRequest.getCreateDate());
        user.setResetPasswordCode(userRequest.getResetPasswordCode());
        user.setBuiltIn(userRequest.isBuiltIn());
        user.setRoles(userRequest.getRoles());
        return user;
    }
}
