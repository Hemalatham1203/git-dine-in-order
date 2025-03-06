package com.example.dio.model;

import com.example.dio.enums.DietType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name="restaurants")
@Getter
@Setter
public class Restaurant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="restaurantId")
    private long restaurantId;

    @Column(name="restaurant_name")
    private String restaurantName;

    @Column(name="address")
    private String address;

    @Column(name="contact_number")
    private String contactNumber;

    @Column(name="email")
    private String email;

    @Column(name="opens_At")
    private LocalTime opensAt;

    @Column(name="close_At")
    private LocalTime closeAt;

    @Column(name="diet_Type")
    @Enumerated(EnumType.STRING)
    private List<DietType> dietTypes;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="last_modified_at")
    private LocalDateTime lastModifiedAt;

    @Column(name="cuisine_Type")
    @ManyToMany(mappedBy = "restaurants",fetch = FetchType.EAGER)
    private List<CuisineType> cuisineTypes;

    @ManyToOne(fetch = FetchType.LAZY)
    private Admin admin;

}
