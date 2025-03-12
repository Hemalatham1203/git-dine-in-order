package com.example.dio.mapper;

import com.example.dio.dto.response.CartItemResponse;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.model.CartItem;
import com.example.dio.model.FoodItem;
import com.example.dio.model.Restaurant_Table;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CartItemMapper {

    /**
     * Maps a CartItem entity to a CartItemResponse DTO.
     *
     * @param cartItem The CartItem entity to be mapped.
     * @return A CartItemResponse object containing the mapped data.
     */
    CartItemResponse mapToCartItemResponse(CartItem cartItem);

}
