package com.example.dio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BillResponse {

    private long billId;

    private List<FoodOrderResponse> orders;

    private LocalDateTime generatedAt;

    private double totalPayableAmount;

}
