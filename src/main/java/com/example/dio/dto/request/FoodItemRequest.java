package com.example.dio.dto.request;

import com.example.dio.enums.DietType;
import com.example.dio.model.CuisineType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FoodItemRequest {

    private String name;

    private double price;

    private String description;

    private int stock;

    private String availability;

    private DietType dietType;

    private String cuisineType;

    private List<String> categories;

}
