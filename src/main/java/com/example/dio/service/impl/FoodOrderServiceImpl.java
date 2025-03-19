package com.example.dio.service.impl;

import com.example.dio.dto.response.CartItemResponse;
import com.example.dio.dto.response.FoodOrderResponse;
import com.example.dio.enums.OrderStatus;
import com.example.dio.mapper.CartItemMapper;
import com.example.dio.mapper.FoodOrderMapper;
import com.example.dio.model.CartItem;
import com.example.dio.model.Food_Order;
import com.example.dio.model.Restaurant_Table;
import com.example.dio.repository.CartItemRepository;
import com.example.dio.repository.FoodOrderRepository;
import com.example.dio.repository.RestaurantTableRepository;
import com.example.dio.service.FoodOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class FoodOrderServiceImpl implements FoodOrderService {

    private final RestaurantTableRepository tableRepository;
    private final CartItemRepository cartItemRepository;
    private final FoodOrderRepository orderRepository;
    private final FoodOrderMapper orderMapper;
    private final CartItemMapper cartItemMapper;

    @Override
    public FoodOrderResponse createOrder(Long tableId) {
        Restaurant_Table table = tableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Table not found with id : " + tableId));


        List<CartItem> cartItems = cartItemRepository.findByIsOrderedAndRestaurantTable_TableId(false, tableId);

        Food_Order foodOrder = new Food_Order();
        foodOrder.setRestaurantTable(table);
        foodOrder.setOrderedAt(LocalTime.now());
        foodOrder.setCartItems(cartItems);

        double totalAmount = calculateTotalAmount(cartItems);
        foodOrder.setTotalAmount(totalAmount);
        foodOrder.setOrderStatus(OrderStatus.UN_BUILD);


        orderRepository.save(foodOrder);
        List<Long> cartItemIds = cartItems.stream()
                .map(CartItem::getCartId)
                .toList();

        List<CartItemResponse> cartItemResponses = cartItems.stream()
                .map(cartItemMapper::mapToCartItemResponse)
                .toList();


        cartItemRepository.updateCartItemsIsOrdered(cartItemIds);

        return orderMapper.mapToOrderResponse(foodOrder);
    }

    public double calculateTotalAmount(List<CartItem> cartItems) {
        double totalAmount = 0;
        for (CartItem c : cartItems) {
            totalAmount += c.getTotalPrice();
        }
        return totalAmount;
    }
}