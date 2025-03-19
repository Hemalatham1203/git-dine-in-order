package com.example.dio.model;

import com.example.dio.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="food_orders")
public class Food_Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orderId")
    private long orderId;

    @Column(name="orderStatus")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name="orderedAt")
    @CreatedDate
    private LocalTime orderedAt;

    @Column(name="totalAmount")
    private double totalAmount;

    @ManyToOne
    private Restaurant_Table restaurantTable;

    @OneToMany
    private List<CartItem> cartItems;

}
