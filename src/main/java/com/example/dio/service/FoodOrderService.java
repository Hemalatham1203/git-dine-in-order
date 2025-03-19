package com.example.dio.service;

import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.dto.response.FoodOrderResponse;
import org.springframework.stereotype.Service;

@Service
public interface FoodOrderService {
    FoodOrderResponse createOrder(Long tableId);
}
