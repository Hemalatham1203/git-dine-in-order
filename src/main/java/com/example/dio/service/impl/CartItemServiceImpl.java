package com.example.dio.service.impl;

import com.example.dio.dto.response.CartItemResponse;
import com.example.dio.mapper.CartItemMapper;
import com.example.dio.model.CartItem;
import com.example.dio.model.FoodItem;
import com.example.dio.model.Restaurant_Table;
import com.example.dio.repository.CartItemRepository;
import com.example.dio.repository.FoodItemRepository;
import com.example.dio.repository.TableRepository;
import com.example.dio.service.CartItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    private final FoodItemRepository foodItemRepository;
    private final TableRepository tableRepository;

    @Override
    public CartItemResponse createCartItem(long itemId, long tableId, int quantity) {

        // Check if food item exists
        FoodItem foodItem = foodItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Food item not found"));

        Restaurant_Table restaurantTable = tableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Table not found by Id"));

        CartItem cartItem = cartItemRepository.save(getCartItem(quantity, foodItem, restaurantTable));
        cartItem.setFoodItem(foodItem);
        cartItem.setRestaurantTable(restaurantTable);
        return cartItemMapper.mapToCartItemResponse(cartItem);
    }

    public CartItemResponse updateQuantity(long cartId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("cart Item not found with Id" + cartId));

        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(cartItem.getFoodItem().getPrice() * quantity);

        return cartItemMapper.mapToCartItemResponse(cartItemRepository.save(cartItem));
    }

    private static CartItem getCartItem(int quantity, FoodItem foodItem, Restaurant_Table restaurantTable) {
        CartItem cartItem = new CartItem();
        cartItem.setFoodItem(foodItem);
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(foodItem.getPrice() * cartItem.getTotalPrice());
        cartItem.setRestaurantTable(restaurantTable);
        return cartItem;
    }
}


