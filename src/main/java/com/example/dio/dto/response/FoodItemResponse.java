package com.example.dio.dto.response;

import com.example.dio.enums.DietType;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class FoodItemResponse {

    private String name;

    private double price;

    private String description;

    private int stock;

    private String availability;

    private DietType dietType;

    private String cuisineType;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedAt;
}
