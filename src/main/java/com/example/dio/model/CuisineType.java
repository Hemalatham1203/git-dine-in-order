package com.example.dio.model;

import jakarta.persistence.*;
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
    private  String cuisine;

    @ManyToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
    private List<Restaurant> restaurant;


}
