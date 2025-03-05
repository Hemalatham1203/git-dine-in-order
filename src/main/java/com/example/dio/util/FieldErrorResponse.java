package com.example.dio.util;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class FieldErrorResponse extends SimpleErrorResponse {
    private List<FieldError> errors;

    /**
     *Creates and returns new FieldError object with the specified error message, rejected value, and field name
     * @param message error message describing the validation issue.
     * @param rejectedValue  that was rejected or failed validation
     * @param field name of the field where the validation error occurred.
     * @return new FieldError object containing the provided error details (message, rejected value, and field name).
     */
    public static FieldError createFieldError(String message,Object rejectedValue,String field){
        FieldError error=new FieldError();
        error.message=message;
        error.rejectedValue=rejectedValue;
        error.field=field;

        return  error;
    }
    @Getter
    public static class FieldError{
        private String message;
        private Object rejectedValue;
        private String  field;
    }

}
