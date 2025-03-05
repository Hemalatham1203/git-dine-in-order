package com.example.dio.dto.response;

import com.example.dio.enums.DietType;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter
@Setter
public class RestaurantResponse {

    private long restaurantId;

    private String restaurantName;

    private String address;

    private long contactNumber;

    private String email;

    private LocalTime opensAt;

    private LocalTime closeAt;

    private DietType dietType;

    private LocalDate createdAt;

    private LocalDate lastModifiedAt;

}
