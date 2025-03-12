package com.example.dio.model;

import com.example.dio.enums.TableStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="restaurant_tables")
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
    private TableStatus tableStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @OneToOne
    private CartItem cartItem;

}
