package com.example.dio.controller;

import com.example.dio.service.QRService;
import com.google.zxing.WriterException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("${app.base-url}")
public class QRCodeController {

    private QRService qrService;

    @Operation(
            summary = "Generate a QR Code",
            description = "Generates a QR code from a given URL and returns it as a PNG image."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "QR Code generated successfully",
                    content = @Content(mediaType = "image/png")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - QR Code generation failed",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(value = "/qr", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQRCode(@RequestParam("url") String url) {
        try {
            // Generate a 250x250 QR code image for the provided URL
            byte[] qrImage = qrService.generateQRCode(url);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrImage);
        } catch (WriterException | IOException e) {
            log.error("Failed to generate QR code message: {}, rootCause: ", e.getMessage(), e.getCause());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
