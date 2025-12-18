package com.example.demo.entity;

import java.util.Date;
import jakarta.persistence.Id;
@Entity
public class DeliveryRecordEntity{
     @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Contract contract;
    private Date deliveryDate;
    private String notes;
    private LocalDateTime createdAt;
}