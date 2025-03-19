package com.example.dio.controller;

import com.example.dio.dto.response.BillResponse;
import com.example.dio.service.BillService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class BillController {

    private final BillService billService;

    @Operation(
            summary = "Generate a bill for a specific table",
            description = "Generates a bill for the given table ID and returns the bill details."
    )
    @ApiResponse(responseCode = "201", description = "Bill successfully generated",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BillResponse.class)))
    @PostMapping("/bills/generate/{tableId}")
    public ResponseEntity<ResponseStructure<BillResponse>> generateBill(@PathVariable long tableId) {
        BillResponse response = billService.createBill(tableId);
        return ResponseBuilder.created(response,"Bill generated successfully");
    }

    @Operation(
            summary = "Find a bill by ID",
            description = "Retrieves bill details for the given bill ID."
    )
    @ApiResponse(responseCode = "200", description = "Bill found successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BillResponse.class)))
    @GetMapping("/bills/{billId}")
    public ResponseEntity<ResponseStructure<BillResponse>> findBill(@PathVariable long billId) {
        BillResponse response = billService.findByBillId(billId);
        return ResponseBuilder.ok(response,"Bill find successfully");
    }

    @Operation(
            summary = "Generate and download a bill as a PDF",
            description = "Generates a bill PDF for the given bill ID and returns it as a downloadable file."
    )
    @ApiResponse(responseCode = "200", description = "PDF generated successfully",
            content = @Content(mediaType = "application/pdf"))
    @GetMapping("/bills/{billId}/pdf")
    public ResponseEntity<byte[]> generatePdf(@PathVariable long billId) throws Exception {
            byte[] pdfBytes = billService.generatePdf(billId);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=bill_" + billId + ".pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);
    }

}
