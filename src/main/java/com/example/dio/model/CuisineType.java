package com.example.dio.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="cuisines")
@Getter
@Setter
public class CuisineType {
    @Id
    @Column(name="cuisine")
    private  String cuisineTypes;

    @ManyToMany(mappedBy ="cuisineTypes",fetch = FetchType.EAGER)
    private List<Restaurant> restaurants;

    @OneToMany(mappedBy = "cuisineType")
    private List<FoodItem> foodItems;

}
