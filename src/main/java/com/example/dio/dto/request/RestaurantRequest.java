package com.example.dio.dto.request;

import com.example.dio.enums.DietType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class RestaurantRequest {
    @NotNull(message="restaurantName cannot be null ")
    @NotBlank(message="restaurantName cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$",message ="restaurantName can only contains alphabets, numbers and underscore")
    private String restaurantName;

    @NotNull(message="address cannot be null ")
    @NotBlank(message="address cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$",message ="restaurantName can only contains alphabets, numbers and underscore")
    private String address;

    @NotNull(message="phone number can not be null")
    @NotBlank(message="password cannot be blank")
    @Pattern(regexp="^[7-9]\\d{9}$",message="Invalid phone number")
    private String contactNumber;

    @NotNull(message="Email cannot be null")
    @NotBlank(message="Email cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+@gmail.com$")
    private String email;

    @NotNull(message="DietType must not be null")
    @NotEmpty(message="DietType must not be empty")
    @Size(min = 1, message = "There must be at least one diet type")
    private List<DietType> dietTypes;

    @NotNull(message="CuisineType must not be null")
    @NotEmpty(message="CuisineType must not be blank")
    @Size(min = 1, message = "There must be at least one cuisine type")
    private List<String> cuisineTypes;

    private LocalTime opensAt;

    private LocalTime closeAt;

}
