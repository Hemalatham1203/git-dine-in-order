package com.example.dio.dto.request;

import com.example.dio.enums.DietType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

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
    @NotBlank(message="DietType must not be blank")
    @Size(min = 1, message = "There must be at least one diet type")
    private List<DietType> dietTypes;

    @NotNull(message="CuisineType must not be null")
    @NotBlank(message="CuisineType must not be blank")
    @Size(min = 1, message = "There must be at least one cuisine type")
    @Pattern(regexp = "^[A-Za-z]+$")
    private List<String> cuisineTypes;

    @NotNull(message = "Opening time cannot be null")
    @Pattern(regexp="^(0?[1-9]|1[0-2]):([0-5]?[0-9]) (AM|PM)$")
    private LocalTime opensAt;

    @NotNull(message = "Closing time cannot be null")
    @Pattern(regexp = "^(0?[1-9]|1[0-2]):([0-5]?[0-9]) (AM|PM)$")
    private LocalTime closeAt;

}
