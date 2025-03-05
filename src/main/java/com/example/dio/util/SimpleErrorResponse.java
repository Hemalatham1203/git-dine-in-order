package com.example.dio.util;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class SimpleErrorResponse {
    private String type;
    private int status;
    private String message; // failed to update the user, The user is not found by the id
}
