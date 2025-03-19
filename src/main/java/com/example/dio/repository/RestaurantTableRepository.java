package com.example.dio.repository;

import com.example.dio.model.Restaurant_Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantTableRepository  extends JpaRepository<Restaurant_Table,Long> {
    @Query("SELECT MAX(t.tableNo) FROM Restaurant_Table t WHERE t.restaurant.restaurantId = :restaurantId")
   Integer findMaxTableNoByRestaurant(@Param("restaurantId") long restaurantId);
}
