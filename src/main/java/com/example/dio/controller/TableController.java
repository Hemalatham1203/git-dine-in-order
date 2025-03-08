package com.example.dio.controller;

import com.example.dio.dto.request.TableRequest;
import com.example.dio.dto.response.TableResponse;
import com.example.dio.model.Restaurant;
import com.example.dio.service.TableService;
import com.example.dio.util.FieldErrorResponse;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
@Tag(name="Table Controller",description = "Collection API Endpoints dealing table data.")
public class TableController {

    private TableService tableService;

    @PostMapping("/restaurants/{restaurantId}/tables")
    @Operation(description = """
            The API Endpoint is used to create a Table.
            The Endpoint requires an restaurantId along with table details.
            """, responses = {
            @ApiResponse(responseCode = "201", description = "Table Created"),
            @ApiResponse(responseCode = "400", description = "Invalid Input",
                    content = {@Content(schema = @Schema(implementation = FieldErrorResponse.class))})
    })
    public ResponseEntity<ResponseStructure<TableResponse>> registerTable(@RequestBody @Valid TableRequest tableRequest, @PathVariable Restaurant restaurantId) {
        TableResponse tableResponse = tableService.registerTable(tableRequest,restaurantId);
        return ResponseBuilder.success(HttpStatus.CREATED, "Table Created", tableResponse);
    }

}

