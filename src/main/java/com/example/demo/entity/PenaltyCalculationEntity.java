package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
@Entity
@Data
@AllArgsConstructor
@NoAr
public class PenaltyCalculationEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer daysDelayed;
    private Double calculatedPenalty;
    private LocalDateTime calculatedAt;
}