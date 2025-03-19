package com.example.dio.model;

import com.example.dio.enums.TableStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "restaurant_tables")
public class Restaurant_Table {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="table_id")
    private long tableId;

    @Column(name="tableNo")
    private int tableNo;

    @Column(name="table_capacity")
    private int tableCapacity;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private TableStatus tableStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "restaurantTable")
    private List<CartItem> cartItem;

    @OneToMany(mappedBy = "restaurantTable")
    private List<Food_Order> Orders;

    @OneToMany(mappedBy = "restaurantTable")
    private List<CartItem> cartItems;

}
