package com.example.dio.mapper;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.model.Category;
import com.example.dio.model.CuisineType;
import com.example.dio.model.FoodItem;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@Component
public interface FoodItemMapper {

   public  FoodItem mapToFoodItemEntity(FoodItemRequest foodItemRequest);

    public FoodItemResponse mapToFoodItemResponse(FoodItem foodItem);

    default String mapToString(CuisineType value) {
        if (value == null) {
            return null;
        }
        return value.getCuisineTypes();
    }

    default String mapToString(Category value) {
        if (value == null) {
            return null;
        }
        return value.getCategoryName();
    }

    default CuisineType mapToCuisineType(String value) {
        if (value == null) {
            return null;
        } else {
            CuisineType type = new CuisineType();
            type.setCuisineTypes(value.toLowerCase());
            return type;
        }
    }

    default Category mapToCategory (String value){
            if (value == null) {
                return null;
            } else {
                Category type = new Category();
                type.setCategoryName(value.toLowerCase());
                return type;
            }
    }

    default List<Category> map(List<String> categories){
        if(categories==null|| categories.isEmpty()){
            return null;
        }
        return categories.stream()
                .map(this::mapToCategory)
                .collect(Collectors.toList());
    }
}