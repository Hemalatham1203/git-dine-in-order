package com.example.dio.exception.handler;

import com.example.dio.exception.RestaurantNotFoundByIdException;
import com.example.dio.exception.UnauthorisedAccessException;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.SimpleErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestaurantExceptionHandler {

    @ExceptionHandler(UnauthorisedAccessException.class)
    public ResponseEntity<SimpleErrorResponse> handleUnauthorisedAccessException(UnauthorisedAccessException ex){
        return ResponseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(RestaurantNotFoundByIdException.class)
    public ResponseEntity<SimpleErrorResponse> handleRestaurantNotFoundByIdException(RestaurantNotFoundByIdException rex){
        return ResponseBuilder.error(HttpStatus.NOT_FOUND,rex.getMessage());
    }

}
