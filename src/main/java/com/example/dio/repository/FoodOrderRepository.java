package com.example.dio.repository;

import com.example.dio.enums.OrderStatus;
import com.example.dio.model.Food_Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodOrderRepository extends JpaRepository<Food_Order,Long> {
    List<Food_Order> findByOrderStatusAndRestaurantTable_TableId( OrderStatus orderStatus,long tableId);

    @Modifying
    @Transactional
    @Query("UPDATE Food_Order f SET f.orderStatus = :status WHERE f.orderId IN :orderIds")
    void updateTableOrderOrderStatus(@Param("orderIds") List<Long> orderIds,
                                     @Param("status") OrderStatus status);
}
