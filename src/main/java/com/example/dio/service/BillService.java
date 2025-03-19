package com.example.dio.service;

import com.example.dio.dto.response.BillResponse;
import org.springframework.stereotype.Service;

@Service
public interface BillService {
    BillResponse createBill(Long tableId);

    BillResponse findByBillId(Long billId);

   byte[] generatePdf(Long billId) throws Exception;


}
