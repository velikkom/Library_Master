package com.tpe.payload.request.user;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class LoginRequest {
    // Getters and Setters
    private String email;
    private String password;

}
