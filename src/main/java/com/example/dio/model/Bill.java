package com.example.dio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;


@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@Setter
@Table(name="bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billId")
    private long billId;

    @CreatedDate
    @Column(name = "generatedAt")
    private LocalDateTime generatedAt;

    @Column(name = "totalPayableAmount")
    private double totalPayableAmount;

    @OneToMany
    private List<Food_Order> orders;

}
