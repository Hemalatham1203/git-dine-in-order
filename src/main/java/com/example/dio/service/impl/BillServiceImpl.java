package com.example.dio.service.impl;

import com.example.dio.dto.response.BillResponse;
import com.example.dio.dto.response.FoodOrderResponse;
import com.example.dio.enums.OrderStatus;
import com.example.dio.mapper.BillMapper;
import com.example.dio.mapper.FoodOrderMapper;
import com.example.dio.model.Bill;
import com.example.dio.model.Food_Order;
import com.example.dio.model.Restaurant;
import com.example.dio.model.Restaurant_Table;
import com.example.dio.repository.BillRepository;
import com.example.dio.repository.FoodItemRepository;
import com.example.dio.repository.FoodOrderRepository;
import com.example.dio.repository.RestaurantTableRepository;
import com.example.dio.service.BillService;
import com.example.dio.service.PdfService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final FoodOrderRepository orderRepository;
    private final RestaurantTableRepository tableRepository;
    private final BillRepository billRepository;
    private final BillMapper billMapper;
    private final FoodOrderMapper orderMapper;
    private final FoodItemRepository foodItemRepository;
    private final PdfService pdfService;

    @Transactional
    @Override
    public BillResponse createBill(Long tableId) {
        Restaurant_Table table=tableRepository.findById(tableId)
                .orElseThrow(()-> new RuntimeException("Table not found with tableId : "+tableId));

        List<Food_Order> orders=orderRepository.findByOrderStatusAndRestaurantTable_TableId(OrderStatus.UN_BUILD,tableId);

        Bill bill=new Bill();
        bill.setOrders(orders);
        double totalPayableAmount = calculateTotalPayableAmount(orders);
        bill.setTotalPayableAmount(totalPayableAmount);
        billRepository.save(bill);

        List<Long> tableOrderIds = getOrderIds(orders);

        mapOrdersListToOrderResponses(orders);

        orderRepository.updateTableOrderOrderStatus(tableOrderIds,OrderStatus.BUILD);


        return billMapper.mapToBillResponse(bill);

    }

    @Override
    public BillResponse findByBillId(Long billId) {
        return billRepository.findById(billId)
                .map(billMapper::mapToBillResponse)
                .orElseThrow(()->new RuntimeException("Failed to find bill, Bill not found by id"));

    }

    public byte[] generatePdf(Long billId) throws IOException {
        BillResponse billResponse = findByBillId(billId);

        long foodItemId = billResponse.getOrders().getFirst().getCartItems().getFirst().getFoodItem().getItemId();
        long tableNo = billResponse.getOrders().getFirst().getRestaurantTable().getTableNo();
        System.out.println("table no" + tableNo);

        Restaurant restaurant = foodItemRepository.findRestaurant_RestaurantNameByItemId(foodItemId);
        String restaurantName = restaurant != null ? restaurant.getRestaurantName() : "";

        Map<String, Object> data = Map.of("restaurantName",restaurantName, "bill", billResponse,"tableNo",tableNo,"foodItemId",foodItemId);
        byte[] pdfBytes = pdfService.generatePdf("bill_view", data);

        return pdfBytes;
    }


    private void mapOrdersListToOrderResponses(List<Food_Order> orders) {
        List<FoodOrderResponse> orderResponses= orders.stream()
                .map(orderMapper::mapToOrderResponse)
                .toList();
    }

    private static List<Long> getOrderIds(List<Food_Order> orders) {
        List<Long> tableOrderIds = orders.stream()
                .map(Food_Order::getOrderId)
                .toList();
        return tableOrderIds;
    }

    public double calculateTotalPayableAmount(List<Food_Order> orders){
        double totalPayableAmount=0;
        for(Food_Order order:orders){
            totalPayableAmount=totalPayableAmount+order.getTotalAmount();

        }
        return totalPayableAmount;
    }
}
