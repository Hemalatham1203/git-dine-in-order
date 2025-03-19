package com.example.dio.mapper;

import com.example.dio.dto.response.BillResponse;
import com.example.dio.model.Bill;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface BillMapper {

    /**
     * Converts a {@link Bill} entity to a {@link BillResponse} DTO.
     *
     * @param bill the bill entity to be mapped
     * @return a {@link BillResponse} containing the mapped bill details,
     *         or {@code null} if the input bill is {@code null}.
     */
    BillResponse mapToBillResponse(Bill bill);
}
