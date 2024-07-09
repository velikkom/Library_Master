package com.tpe.payload.request.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tpe.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class UserRequest extends BaseUserRequest {

    @NotBlank(message = "First name is required")
    @Size(max = 30, message = "First name must be at most 30 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 30, message = "Last name must be at most 30 characters")
    private String lastName;

    @Min(value = 0, message = "Score must be at least 0")
    private int score;

    @NotBlank(message = "Address is required")
    @Size(max = 100, message = "Address must be at most 100 characters")
    private String address;

    @NotBlank(message = "Phone number is required")
    @Size(max = 12, message = "Phone number must be at most 12 characters")
    private String phone;

    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

   /* @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 80, message = "Email must be at most 80 characters")
    private String email;*/

  /*  @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;*/

    @NotNull(message = "Create date is required")
    private LocalDateTime createDate;

    private String resetPasswordCode;

    private boolean builtIn;

    @NotEmpty(message = "Roles are required")
    private Set<Role> roles;

}
