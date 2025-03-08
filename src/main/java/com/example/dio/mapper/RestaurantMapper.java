package com.example.dio.mapper;

import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.model.CuisineType;
import com.example.dio.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    /**
     *This method is responsible for mapping a RestaurantRequest object (which typically contains data entered by the user) to a RestaurantEntity object.
     * @param restaurantRequest contains information about the restaurant, such as its name, location, menu items, etc., submitted by the user.
     * @return RestaurantEntity object that represents the restaurant's data in a format suitable for storage in a database.
     */
    Restaurant mapToRestaurantEntity(RestaurantRequest restaurantRequest);

    /**
     *The purpose of these method is to convert the database entity into a form that can be sent back to the client, usually containing only the necessary fields.
     * @param restaurant contains the restaurant's data.
     * @return a RestaurantResponse object that contains the necessary data to be returned in an API response.
     */
    RestaurantResponse mapToRestaurantResponse(Restaurant restaurant);

    /**
     *This method is responsible for converting a CuisineType enum or object to its corresponding String representation. If the CuisineType is null, the method returns null. The CuisineType object is expected to have a method getCuisineType() that returns a String representing the cuisine.
     * @param cuisineType represents a specific cuisine, and the method will retrieve its string representation via the getCuisineType() method.
     * @return String representation of the CuisineType. If the cuisineType is null, the method returns null.
     */
    default String mapToString(CuisineType cuisineType) {
        if (cuisineType == null) {
            return null;
        }
        return cuisineType.getCuisineTypes();
    }

    /**
     *This method is responsible for converting a String representation of a cuisine type to a CuisineType object.
     * @param cuisineType  representing the cuisine type.
     * @return new CuisineType object with its cuisineType property set to the provided string. If the input string is null, the method returns null.
     */
    default CuisineType mapToCuisineType(String cuisineType) {
        if (cuisineType == null) {
            return null;
        }
        CuisineType cuisine = new CuisineType();
        cuisine.setCuisineTypes(cuisineType);
        return cuisine;
    }
}

