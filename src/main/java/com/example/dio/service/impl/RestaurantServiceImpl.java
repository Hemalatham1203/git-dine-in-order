package com.example.dio.service.impl;

import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.exception.UnauthorisedAccessException;
import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.mapper.RestaurantMapper;
import com.example.dio.model.Admin;
import com.example.dio.model.CuisineType;
import com.example.dio.model.Restaurant;
import com.example.dio.model.User;
import com.example.dio.repository.CuisineTypeRepository;
import com.example.dio.repository.RestaurantRepository;
import com.example.dio.repository.UserRepository;
import com.example.dio.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;
    private CuisineTypeRepository cuisineTypeRepository;
    private RestaurantMapper restaurantMapper;
    private UserRepository userRepository;

    /**
     * Creates a new restaurant and assigns it to an admin user.
     * @param restaurantRequest The request object containing restaurant details.
     * @param userId The user attempting to create the restaurant.
     * @return A RestaurantResponse DTO with the created restaurant details.
     * @throws UserNotFoundByIdException If the provided user ID does not exist.
     * @throws UnauthorisedAccessException If the user is not an admin.
     */
    @Override
    public RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest, User userId) {
        User user=userRepository.findById(userId.getUserId())
                .orElseThrow(() -> new UserNotFoundByIdException("User with Id"+userId+"not found"));
        if (!(user instanceof Admin admin)) {
            throw new UnauthorisedAccessException("Only Admin is allowed to create a Restaurant");
        }
        Restaurant restaurant=restaurantMapper.mapToRestaurantEntity(restaurantRequest);
        restaurant.setAdmin(admin);

        List<CuisineType> exCuisine=restaurant.getCuisineTypes();
        List<CuisineType> newCuisines=createNonExistingCuisines(exCuisine);
        restaurant.setCuisineTypes(newCuisines);

        restaurantRepository.save(restaurant);

        return restaurantMapper.mapToRestaurantResponse(restaurant);
    }

    /**
     * Checks if the given list of cuisine types exists in the database.
     * If a cuisine type does not exist, it is saved to the database.
     * @param cuisineTypes The list of cuisine types to check or create.
     * @return A list of cuisine types, ensuring all exist in the database.
     */
    private List<CuisineType> createNonExistingCuisines(List<CuisineType> cuisineTypes){
        return cuisineTypes.stream()
                .map(exCuisine -> cuisineTypeRepository.findById(exCuisine.getCuisineTypes())
                        .orElseGet(() -> cuisineTypeRepository.save(exCuisine)))
                .toList();
    }
}
