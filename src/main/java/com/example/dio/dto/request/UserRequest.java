package com.example.dio.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    @NotNull(message="User name cannot be null or blank")
    @NotBlank(message="User cannot be blank")
    private String username;
    @NotNull(message="user name cannot be null or blank")
    @NotBlank(message = "User cannot be blank")
    private String email;
    private String phno;
}
