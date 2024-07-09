package com.tpe.payload.request.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SaveUserRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private LocalDate birthDate;
    private String role;
}
