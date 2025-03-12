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


    /**
     * Creates a new FoodItem for the given restaurant.
     * @param restaurantId    The ID of the restaurant where the food item will be created.
     * @param foodItemRequest The request object containing details of the food item.
     * @return A FoodItemResponse containing details of the created food item.
     * @throws RestaurantNotFoundByIdException if the restaurant with the given ID is not found.
     */
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

    /**
     * Ensures that each category in the provided list exists in the repository.
     * If a category does not exist, it is saved and then added to the list.
     * @param categories A list of Category objects to verify.
     * @return A list of Category objects that exist in the repository.
     */
    private List<Category> createNonExistingCategory(List<Category> categories){
        return categories.stream()
                .map( type -> categoryRepository.findById(type.getCategoryName())
                        .orElseGet(() -> categoryRepository.save(type)))
                .toList();
    }

    /**
     * Retrieves a list of food items that match the specified categories.
     *
     * @param categoryName A list of category names to filter food items.
     * @return A list of FoodItemResponse objects corresponding to the filtered food items.
     */
    @Override
    public List<FoodItemResponse> getFoodItemsByCategory(List<String> categoryName) {
        long categoryCount = categoryName.size();
        List<FoodItem> foodItems = foodItemRepository.findFoodItemsByCategoryName(categoryName,categoryCount);
        return foodItems.stream()
                .map(foodItemMapper::mapToFoodItemResponse)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all food items available in a specific restaurant.
     *
     * @param restaurantId The ID of the restaurant.
     * @return A list of FoodItemResponse objects for the food items associated with the restaurant.
     */
    @Override
    public List<FoodItemResponse> getFoodItemsByRestaurant(long restaurantId) {
        List<FoodItem> foodItems=foodItemRepository.findFoodItemsByRestaurantId(restaurantId);
        return foodItems.stream()
                .map(foodItemMapper::mapToFoodItemResponse)
                .collect(Collectors.toList());
    }

    /**
     * Updates the availability status of a food item based on its stock.
     *
     * @param stock        The current stock of the food item.
     * @param availability A placeholder for the availability status (input is overridden).
     * @return "AVAILABLE" if stock is greater than 0; otherwise, "OUT OF STOCK".
     */
    public String updateFoodAvailability(int stock,String availability){
        availability=(stock>0)? "AVAILABLE":"OUT OF STOCK";
        return availability;
    }
}
