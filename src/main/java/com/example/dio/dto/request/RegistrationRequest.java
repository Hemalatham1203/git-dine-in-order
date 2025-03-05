package com.example.dio.dto.request;

import com.example.dio.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegistrationRequest {

    @NotNull(message="userName cannot be null ")
    @NotBlank(message="userName cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$",message ="UserName can only contains alphabets, numbers and underscore")
    private  String username;

    @NotNull(message="Email cannot be null")
    @NotBlank(message="Email cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+@gmail.com$")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(/=.*[A-Z])(?=.*d)(?=.*[&*^%$#@!+-_]).{8,12}$",message="Password must contain at least one lowercase letter, upper case letter, number,special character among them")
    @NotNull(message="password can not be null")
    @NotBlank(message="password cannot be blank")
    private String password;

    @NotNull(message="phone number can not be null")
    @NotBlank(message="password cannot be blank")
    @Pattern(regexp="^[7-9]\\d{9}$",message="Invalid phone number")
    private String phno;

    private UserRole role;

}
