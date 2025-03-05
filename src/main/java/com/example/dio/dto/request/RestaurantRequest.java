package com.example.dio.dto.request;

import com.example.dio.enums.DietType;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class RestaurantRequest {

    private long restaurantId;

    private String restaurantName;

    private String address;

    private long contactNumber;

    private String email;

    private List<DietType> dietType;

    private List<String> cuisineTypes;

    private LocalTime opensAt;

    private LocalTime closeAt;

}
