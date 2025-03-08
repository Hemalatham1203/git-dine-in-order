package com.example.dio.service;

import com.example.dio.dto.request.TableRequest;
import com.example.dio.dto.response.TableResponse;
import com.example.dio.model.Restaurant;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface TableService {
    /**
     * Registers a new table for a given restaurant.
     * @param tableRequest The request object containing table details.
     * @param restaurantId The restaurant to which the table belongs.
     * @return A TableResponse DTO with the registered table details.
     */
    TableResponse registerTable(@Valid TableRequest tableRequest, Restaurant restaurantId);
}
