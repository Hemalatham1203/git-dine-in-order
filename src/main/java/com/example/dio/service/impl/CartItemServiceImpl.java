package com.example.dio.service.impl;

import com.example.dio.dto.response.CartItemResponse;
import com.example.dio.mapper.CartItemMapper;
import com.example.dio.model.CartItem;
import com.example.dio.model.FoodItem;
import com.example.dio.model.Restaurant_Table;
import com.example.dio.repository.CartItemRepository;
import com.example.dio.repository.FoodItemRepository;
import com.example.dio.repository.RestaurantTableRepository;
import com.example.dio.service.CartItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    private final FoodItemRepository foodItemRepository;
    private final RestaurantTableRepository restaurantTableRepository;

    @Override
    public CartItemResponse createCartItem(long itemId, long tableId, int quantity) {

        // Check if food item exists
        FoodItem foodItem = foodItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Food item not found"));

        Restaurant_Table restaurantTable = restaurantTableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Table not found by Id"+tableId));

        CartItem cartItem=new CartItem();
        cartItem.setFoodItem(foodItem);
        cartItem.setRestaurantTable(restaurantTable);
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(calculateTotalPrice(foodItem,quantity));
        cartItemRepository.save(cartItem);

        return cartItemMapper.mapToCartItemResponse(cartItem);

    }

    @Override
    public CartItemResponse updateCartItemQuantity(Long cartId, int newQuantity) {
        CartItem cartItem=cartItemRepository.findById(cartId)
                .orElseThrow(()->new RuntimeException("Cart item not found with id: "+cartId));
        cartItemRepository.updateQuantityByCartItemId(cartId,newQuantity);
        cartItem.setCartId(newQuantity);
        cartItem.setTotalPrice(calculateTotalPrice(cartItem.getFoodItem(),newQuantity));
        return cartItemMapper.mapToCartItemResponse(cartItem);
    }

    public double calculateTotalPrice(FoodItem foodItem,int quantity){
        return foodItem.getPrice()*quantity;

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


