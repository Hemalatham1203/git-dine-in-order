package com.example.dio.repository;

import com.example.dio.model.Restaurant_Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Restaurant_Table,Long> {
}
