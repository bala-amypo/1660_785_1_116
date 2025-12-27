package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //added
public class BreachReport {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Contract contract;

    private Integer daysDelayed;
    private BigDecimal penaltyAmount;
    private LocalDateTime generatedAt;

    @PrePersist
    void onGen() {
        generatedAt = LocalDateTime.now();
    }
}
