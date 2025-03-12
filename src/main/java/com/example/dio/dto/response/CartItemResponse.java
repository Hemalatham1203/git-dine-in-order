package com.example.dio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartItemResponse {

    private long cartId;

    private int quantity;

    private double totalPrice;

    private String isOrdered;
}
