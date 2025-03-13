package com.example.dio.service;

import org.springframework.stereotype.Service;

@Service
public interface QRService {
    /**
     * Generates a QR code as a byte array from the given URL.
     *
     * @param url The URL to be encoded into the QR code.
     * @return A byte array representing the QR code image in PNG format.
     * @throws Exception If an error occurs during QR code generation.
     */
    byte[] generateQRCode(String url) throws Exception;
}
