package com.example.dio.dto.response;

import com.example.dio.enums.OrderStatus;
import com.example.dio.model.CartItem;
import com.example.dio.model.Restaurant_Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodOrderResponse {

    private long orderId;

    private OrderStatus orderStatus;

    private LocalTime orderedAt;

    private Restaurant_Table restaurantTable;

    private List<CartItemResponse> cartItems;

    private double totalAmount;
}
