package com.example.dio.mapper;

import com.example.dio.dto.response.FoodOrderResponse;
import com.example.dio.model.Food_Order;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
public interface FoodOrderMapper {

    /**
     * Converts a {@link Food_Order} entity to a {@link FoodOrderResponse} DTO.
     *
     * @param foodOrder the food order entity to be mapped
     * @return a {@link FoodOrderResponse} containing the mapped order details,
     *         or {@code null} if the input food order is {@code null}.
     */
    FoodOrderResponse mapToOrderResponse(Food_Order foodOrder);
}
