package com.example.dio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cartId")
    private long cartId;

    @Column(name="quantity")
    private int quantity;

    @Column(name="totalPrice")
    private double totalPrice;

    @Column(name="isOrdered")
    private boolean isOrdered;

    @OneToOne
    private Restaurant_Table restaurantTable;

    @ManyToOne
    private FoodItem foodItem;
}
