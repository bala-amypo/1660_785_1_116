package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BreachReport {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Contract contract;

    private Integer daysDelayed;
    private BigDecimal penaltyAmount;
    private LocalDateTime generatedAt = LocalDateTime.now();
}
