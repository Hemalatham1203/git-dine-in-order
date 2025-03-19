package com.example.dio.dto.request;

import com.example.dio.enums.TableStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableRequest {

    @NotNull(message="Table capacity cannot be null ")
    @Min(value = 1, message = "Table capacity must be at least 1")
    private int tableCapacity;

    private TableStatus tableStatus;
}
