package com.example.dio.service;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.model.FoodItem;
import com.example.dio.model.Restaurant;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface FoodItemService {

    /**
     * Creates a new food item for a specific restaurant.
     * @param restaurantId    The ID of the restaurant where the food item will be created.
     * @param foodItemRequest The request object containing food item details.
     * @return The response object containing details of the created food item.
     */
    FoodItemResponse createFoodItem(long restaurantId, FoodItemRequest foodItemRequest);

    /**
     * Retrieves a list of food items based on the specified categories.
     * @param categories A list of food categories to filter food items.
     * @return A list of food items that match the given categories.
     */
    List<FoodItemResponse> getFoodItemsByCategory(List<String> categories);

    /**
     * Fetches all food items available in a specific restaurant.
     * @param restaurantId The ID of the restaurant.
     * @return A list of food items associated with the specified restaurant.
     */
    List<FoodItemResponse> getFoodItemsByRestaurant(long restaurantId);
}
