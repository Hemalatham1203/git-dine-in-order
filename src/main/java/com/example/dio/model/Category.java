package com.example.dio.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="category")
public class Category {
    @Id
    @Column(name="category")
    private String categoryName;

    @ManyToMany(mappedBy = "categories")
    private List<FoodItem> foodItems;
}
