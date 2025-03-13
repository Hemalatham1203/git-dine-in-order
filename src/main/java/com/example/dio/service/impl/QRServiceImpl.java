package com.example.dio.service.impl;

import com.example.dio.service.QRService;
import com.example.dio.util.QRCodeGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QRServiceImpl implements QRService {

    /**
     * Generates a QR code image from the given URL.
     *
     * @param url The URL to be encoded into the QR code.
     * @return A byte array containing the QR code image in PNG format.
     * @throws Exception If an error occurs during QR code generation.
     */
    @Override
    public byte[] generateQRCode(String url) throws Exception{
        return QRCodeGenerator.generateQRCodeImage(url);
    }
}
