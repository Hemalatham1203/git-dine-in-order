package com.example.dio.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestaurantNotFoundByIdException extends RuntimeException {
    private final String message;
}
