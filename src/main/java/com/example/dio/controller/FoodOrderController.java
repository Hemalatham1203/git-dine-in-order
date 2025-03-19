package com.example.dio.controller;

import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.dto.response.FoodOrderResponse;
import com.example.dio.service.FoodOrderService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class FoodOrderController {

    private final FoodOrderService orderService;

    @Operation(
            summary = "Create a new food order",
            description = "Creates a new food order for a given table ID and returns the order details."
    )
    @ApiResponse(responseCode = "201", description = "Order successfully created",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = FoodOrderResponse.class)))
    @ApiResponse(responseCode = "400", description = "Invalid table ID")
    @PostMapping("/orders/tables/{tableId}")
    public ResponseEntity<ResponseStructure<FoodOrderResponse>> createOrder(@PathVariable long tableId){
        FoodOrderResponse orderResponse=orderService.createOrder(tableId);
        return ResponseBuilder.created(orderResponse,"Order created ");
    }

}
