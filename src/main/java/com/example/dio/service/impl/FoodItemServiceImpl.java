package com.example.dio.service.impl;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.exception.RestaurantNotFoundByIdException;
import com.example.dio.mapper.FoodItemMapper;
import com.example.dio.model.Category;
import com.example.dio.model.CuisineType;
import com.example.dio.model.FoodItem;
import com.example.dio.model.Restaurant;
import com.example.dio.repository.CategoryRepository;
import com.example.dio.repository.CuisineTypeRepository;
import com.example.dio.repository.FoodItemRepository;
import com.example.dio.repository.RestaurantRepository;
import com.example.dio.service.FoodItemService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FoodItemServiceImpl implements FoodItemService{
    private final FoodItemRepository foodItemRepository;
    private  RestaurantRepository restaurantRepository;
    private  FoodItemMapper foodItemMapper;
    private CuisineTypeRepository cuisineTypeRepository;
    private  CategoryRepository categoryRepository;

    @Transactional
    @Override
    public FoodItemResponse createFoodItem(long restaurantId, FoodItemRequest foodItemRequest) {
        FoodItem foodItem = foodItemMapper.mapToFoodItemEntity(foodItemRequest);
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundByIdException("Restaurant not found by id" + restaurantId));

        cuisineTypeRepository.findById(foodItem.getCuisineType().getCuisineTypes())
                .orElseGet(() -> {
                    CuisineType cuisineType = cuisineTypeRepository.save(foodItem.getCuisineType());
                    restaurant.getCuisineTypes().add(cuisineType);
                    restaurantRepository.save(restaurant);
                    return cuisineType;
                });

        foodItem.setCategories(this.createNonExistingCategory(foodItem.getCategories()));
        foodItem.setRestaurant(restaurant);
        foodItem.setCuisineType(foodItem.getCuisineType());

        foodItemRepository.save(foodItem);

        return foodItemMapper.mapToFoodItemResponse(foodItem);
    }

    private List<Category> createNonExistingCategory(List<Category> categories){
        return categories.stream()
                .map( type -> categoryRepository.findById(type.getCategoryName())
                        .orElseGet(() -> categoryRepository.save(type)))
                .toList();
    }


    @Override
    public List<FoodItemResponse> getFoodItemsByCategory(List<String> categoryName) {
        long categoryCount = categoryName.size();
        List<FoodItem> foodItems = foodItemRepository.findFoodItemsByCategoryName(categoryName,categoryCount);
        return foodItems.stream()
                .map(foodItemMapper::mapToFoodItemResponse)
                .collect(Collectors.toList());
    }

    public String updateFoodAvailability(int stock,String availability){
        availability=(stock>0)? "AVAILABLE":"OUT OF STOCK";
        return availability;
    }
}
