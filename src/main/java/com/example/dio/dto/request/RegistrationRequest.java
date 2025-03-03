package com.example.dio.dto.request;

import com.example.dio.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegistrationRequest {
    @NotEmpty(message="userName cannot be null or blank")
    @NotBlank(message="userName cannot be blank")
    private  String username;
    @NotEmpty(message="Email cannot be null or blank")
    @NotBlank(message="Email cannot be blank")
    private String email;
    private String password;
    private String phno;
    private UserRole role;

}
