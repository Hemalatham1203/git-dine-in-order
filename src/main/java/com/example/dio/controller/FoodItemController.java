package com.example.dio.controller;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.model.FoodItem;
import com.example.dio.service.FoodItemService;
import com.example.dio.util.FieldErrorResponse;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
@Tag(name="FoodItem Controller",description = "Collection API Endpoints dealing Food Items in a restaurant.")
public class FoodItemController {

    private FoodItemService foodItemService;


    @Operation(description = "Create a new FoodItem ",
            responses = {
                    @ApiResponse(responseCode = "201", description = "FoodItem Created"),
                    @ApiResponse(responseCode = "400", description = "Invalid Input", content = {
                            @Content(schema = @Schema(implementation = FieldErrorResponse.class))
                    }),
                    @ApiResponse(responseCode = "403", description = "FoodItem is not present", content = {
                            @Content(schema = @Schema(implementation = FieldErrorResponse.class))
                    })
            })
    @PostMapping("/foodItems/restaurants/{restaurantId}")
    public ResponseEntity<ResponseStructure<FoodItemResponse>> createRestaurant(@PathVariable Long restaurantId, @RequestBody @Valid FoodItemRequest foodItemRequest) {
        FoodItemResponse foodItemResponse = foodItemService.createFoodItem(restaurantId,foodItemRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseStructure.create(HttpStatus.CREATED,"Food item created",foodItemResponse));
    }


    @Operation(description = "Find food items by category",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Food items retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Category not found", content = {
                            @Content(schema = @Schema(implementation = FieldErrorResponse.class))
                    })
            })
    @GetMapping("/foodItems/category")
    public ResponseEntity<ResponseStructure<List<FoodItemResponse>>> getFoodItemByCategory(@RequestParam("category") List<String> categories) {
        // Fetch FoodItem entities from the database
        List<FoodItemResponse> foodItemResponses = foodItemService.getFoodItemsByCategory(categories);

        return ResponseEntity.status(HttpStatus.FOUND)
                .body(ResponseStructure.create(HttpStatus.FOUND,"Food Items found",foodItemResponses));
    }

}
