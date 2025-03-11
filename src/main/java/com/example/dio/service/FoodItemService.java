package com.example.dio.service;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.model.FoodItem;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface FoodItemService {

    FoodItemResponse createFoodItem(long restaurantId, FoodItemRequest foodItemRequest);

    List<FoodItemResponse> getFoodItemsByCategory(List<String> categories);
}
