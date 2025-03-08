package com.example.dio.service;

import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.model.Restaurant;
import com.example.dio.model.User;
import org.hibernate.engine.spi.Resolution;
import org.springframework.stereotype.Service;

@Service
public interface RestaurantService {

    /**
     * Creates a new restaurant and associates it with a user.
     * @param restaurantRequest The request object containing restaurant details.
     * @param userId The user who owns or manages the restaurant.
     * @return A RestaurantResponse DTO with the created restaurant details.
     */
    RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest, User userId);

}
