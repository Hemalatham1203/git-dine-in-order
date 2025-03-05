package com.example.dio.exception.handler;

import com.example.dio.util.FieldErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class FieldErrorExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<FieldErrorResponse.FieldError> errors = new ArrayList<>();

        List<ObjectError> objectErrors = ex.getAllErrors();

        for (ObjectError objectError:objectErrors){
            FieldError fieldError = (FieldError) objectError;
            FieldErrorResponse.FieldError error = createFielderror( fieldError);
            errors.add(error);
        }

        FieldErrorResponse error = createFeildErrorResponse(status, errors);

        return ResponseEntity.status(status)
                .body(error);


    }

    private static FieldErrorResponse createFeildErrorResponse(HttpStatusCode status, List<FieldErrorResponse.FieldError> errors) {
        return FieldErrorResponse.builder()
                .type(status.toString())
                .message("Invalid input")
                .status(status.value())
                .errors(errors)
                .build();
    }

    private static FieldErrorResponse.FieldError createFielderror(FieldError fieldError) {
        return FieldErrorResponse.createFieldError(
                fieldError.getDefaultMessage(),
                fieldError.getRejectedValue(),
                fieldError.getField());
    }
}
