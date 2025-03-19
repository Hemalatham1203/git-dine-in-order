package com.example.dio.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public interface PdfService {
    byte[] generatePdf(String templateName, Map<String,Object> data) throws IOException;

}
