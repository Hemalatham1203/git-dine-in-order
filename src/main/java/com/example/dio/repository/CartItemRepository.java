package com.example.dio.repository;

import com.example.dio.dto.response.CartItemResponse;
import com.example.dio.model.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    List<CartItem> findByIsOrderedAndRestaurantTable_TableId(boolean isOrdered, long tableId);

    @Modifying
    @Transactional
    @Query("UPDATE CartItem c SET c.quantity = :newQuantity WHERE c.cartId = :cartItemId")
    int updateQuantityByCartItemId(Long cartItemId, int newQuantity);

    @Modifying
    @Transactional
    @Query("UPDATE CartItem c SET c.isOrdered=true WHERE c.cartId IN :cartIds")
    void updateCartItemsIsOrdered(@Param("cartIds") List<Long> cartIds);
    

}
