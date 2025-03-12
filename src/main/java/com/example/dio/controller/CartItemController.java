package com.example.dio.controller;

import com.example.dio.dto.response.CartItemResponse;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.service.CartItemService;
import com.example.dio.util.FieldErrorResponse;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
@Tag(name="CartItem Controller",description = "Collection API Endpoints dealing CartItem added with FoodItems.")
public class CartItemController {


    private final CartItemService cartItemService;

    @Operation(
            description = "Add a food item to the cart for a specific table",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Cart item successfully added"),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = {
                            @Content(schema = @Schema(implementation = FieldErrorResponse.class))
                    }),
                    @ApiResponse(responseCode = "403", description = "Food item or table is not present", content = {
                            @Content(schema = @Schema(implementation = FieldErrorResponse.class))
                    })
            }
    )
    @PostMapping("/tables/{tableId}/cartItems/foodItems/{itemId}")
    public ResponseEntity<ResponseStructure<CartItemResponse>> addToCart(@PathVariable long itemId,@PathVariable long tableId,@RequestParam int quantity){
        CartItemResponse response=cartItemService.createCartItem(itemId,tableId,quantity);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseStructure.create(HttpStatus.CREATED,"cart created",response));
    }


    @Operation(
            description = "Update the quantity of a food item in the cart",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cart item quantity updated successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = {
                            @Content(schema = @Schema(implementation = FieldErrorResponse.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Cart item not found", content = {
                            @Content(schema = @Schema(implementation = FieldErrorResponse.class))
                    })
            }
    )
    @PatchMapping("cartItems/{cartId}")
    public ResponseEntity<ResponseStructure<CartItemResponse>> updateQuantity(@PathVariable long cartId, @RequestParam Long foodItemId, @RequestParam int quantity){
        CartItemResponse cartItemResponse=cartItemService.updateQuantity(cartId,quantity);
        return  ResponseBuilder.ok(cartItemResponse,"Food item added to the cart successfully");
    }

}
