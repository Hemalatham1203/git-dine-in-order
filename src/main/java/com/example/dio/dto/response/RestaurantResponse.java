package com.example.dio.dto.response;

import com.example.dio.enums.DietType;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class RestaurantResponse {

    private String restaurantName;

    private String address;

    private String contactNumber;

    private String email;

    private LocalTime opensAt;

    private LocalTime closeAt;

    private List<DietType> dietTypes;

    private List<String> cuisineTypes;

}
