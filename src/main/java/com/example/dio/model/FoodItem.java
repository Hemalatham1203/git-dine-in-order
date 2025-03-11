package com.example.dio.model;

import com.example.dio.enums.DietType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="Food_Items",indexes = {
        @Index(name="idx_name",columnList = "name"),
})
@EntityListeners(AuditingEntityListener.class)
public class FoodItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="itemId")
    private long itemId;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private double price;

    @Column(name="description")
    private String description;

    @Column(name="stock")
    private int stock;

    @Column(name="availability")
    private String availability;

    @Column(name="diet_Type")
    @Enumerated(EnumType.STRING)
    private DietType dietType;

    @CreatedDate
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="last_modified_at")
    private LocalDateTime lastModifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @ManyToOne(fetch=FetchType.LAZY)
    private CuisineType cuisineType;

    @ManyToMany
    private List<Category> categories;
}
