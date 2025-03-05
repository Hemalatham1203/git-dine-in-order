package com.example.dio.model;

import com.example.dio.enums.DietType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name="restaurant")
@Getter
@Setter
public class Restaurant {
    @Id
    @Column(name="restaurantId")
    private long restaurantId;

    @Column(name="restaurant_name")
    private String restaurantName;

    @Column(name="address")
    private String address;

    @Column(name="contact_number")
    private long contactNumber;

    @Column(name="email")
    private String email;

    @Column(name="opens_At")
    private LocalTime opensAt;

    @Column(name="close_At")
    private LocalTime closeAt;

    @Column(name="diet_Type")
    private List<DietType> dietType;

    @Column(name="created_at")
    private LocalDate createdAt;

    @Column(name="last_modified_at")
    private LocalDate lastModifiedAt;

    @Column(name="cuisine_Type")
    @ManyToMany
    private List<String> cuisineTypes;

}
