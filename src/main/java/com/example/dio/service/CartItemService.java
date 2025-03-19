package com.example.dio.service;

import com.example.dio.dto.response.CartItemResponse;
import com.example.dio.dto.response.FoodItemResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartItemService {

    /**
     * Adds a food item to the cart for a specific table.
     * @param itemId   The ID of the food item to be added.
     * @param tableId  The ID of the table where the item is added.
     * @param quantity The quantity of the food item to be added.
     * @return The response object containing details of the cart item.
     */
    CartItemResponse createCartItem(long itemId, long tableId, int quantity);

    /**
     * Updates the quantity of an existing cart item.
     * @param cartId   The ID of the cart item to be updated.
     * @param newQuantity The new quantity of the food item.
     * @return The updated cart item response.
     */
    CartItemResponse updateCartItemQuantity(Long cartId, int newQuantity);
}
