package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
public class BreachRepositoryEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Contract contract;
    private LocalTimeDate reportGeneratedAt;
    private Integer daysDelayed;
    private BigDecimal penaltyAmount;
    private String remarks;
}