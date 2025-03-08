package com.example.dio.controller;


import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.model.User;
import com.example.dio.service.RestaurantService;
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


@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
@Tag(name="Restaurant Controller",description = "Collection API Endpoints dealing restaurant data.")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/users/{userId}/restaurant")
    @Operation(description = """
            The API Endpoint is used to create a Restaurant.
            The Endpoint requires an ADMIN role along with restaurant details.
            """, responses = {
            @ApiResponse(responseCode = "201", description = "Restaurant Created"),
            @ApiResponse(responseCode = "400", description = "Invalid Input",
                    content = {@Content(schema = @Schema(implementation = FieldErrorResponse.class))})
    })
    public ResponseEntity<ResponseStructure<RestaurantResponse>> createRestaurant(@RequestBody @Valid RestaurantRequest restaurantRequest, @PathVariable User  userId){
        RestaurantResponse response = restaurantService.createRestaurant(restaurantRequest, userId);
        return ResponseBuilder.success(HttpStatus.CREATED, "Restaurant created", response);
    }

}
