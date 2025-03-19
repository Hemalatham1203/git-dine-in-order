package com.example.dio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartItemResponse {

    private long cartId;

    private FoodItemResponse foodItem;

    private int quantity;

    private double totalPrice;

    private boolean isOrdered;
}
