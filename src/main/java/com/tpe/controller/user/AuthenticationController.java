package com.tpe.controller.user;


import com.tpe.payload.request.user.LoginRequest;
import com.tpe.payload.response.authantication.AuthResponse;

import com.tpe.service.user.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    //http://localhost:8080/auth/signin + post
    @PostMapping("/signin")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {

        return authenticationService.authenticateUser(loginRequest);
    }


}
