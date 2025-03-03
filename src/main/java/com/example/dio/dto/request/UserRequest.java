package com.example.dio.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    @NotEmpty(message="User name cannot be null or blank")
    @NotBlank(message="User cannot be blank")
    private String username;
    @NotEmpty(message="user name cannot be null or blank")
    @NotBlank(message = "User cannot be blank")
    private String email;
    private String phno;
}
