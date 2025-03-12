package com.example.dio.repository;

import com.example.dio.model.FoodItem;
import com.example.dio.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodItemRepository extends JpaRepository<FoodItem,Long> {
    @Query("SELECT f FROM FoodItem f JOIN f.categories c WHERE c.categoryName IN :categories GROUP BY f HAVING COUNT(DISTINCT c.categoryName) = :categoryCount")
    List<FoodItem> findFoodItemsByCategoryName(@Param("categories") List<String> categoryNames,@Param("categoryCount") Long categoryCount);

    @Query("SELECT f FROM FoodItem f WHERE f.restaurant.id = :restaurantId")
    List<FoodItem> findFoodItemsByRestaurantId(@Param("restaurantId")long restaurantId);
}
