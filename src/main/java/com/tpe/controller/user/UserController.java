package com.tpe.controller.user;

import com.tpe.entity.user.User;
import com.tpe.payload.mapper.UserMapper;
import com.tpe.payload.request.user.SaveUserRequest;
import com.tpe.payload.request.user.UserRequest;
import com.tpe.payload.request.user.UserSignIn;
import com.tpe.payload.response.business.ResponseMessage;
import com.tpe.payload.response.user.UserResponse;
import com.tpe.payload.response.user.UserSignInResponse;
import com.tpe.service.helper.UserHelper;
import com.tpe.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserHelper userHelper;
    private final UserMapper userMapper;


    /*//http://localhost:8080/users/saveUser + json +'ADMIN','EMPLOYEE'
    @PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE')")
    @PostMapping("/save")
    public ResponseEntity<UserResponse> saveMember(@RequestBody @Valid UserRequest userRequest)
    {
      return ResponseEntity.ok  (userService.saveUser(userRequest));
    }*/


   /* @PreAuthorize("hasAnyAuthority('MEMBER','ADMIN','EMPLOYEE')")
    @PostMapping("/saveUser")
    public ResponseEntity<UserResponse> saveUser(
            @RequestBody @Valid UserRequest userRequest)
    {

       User user = userService.saveUser(userRequest);
       UserResponse userResponse = userMapper.toResponse(user);
       return ResponseEntity.ok(userResponse);
    }*/







}
