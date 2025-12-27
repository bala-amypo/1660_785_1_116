// package com.example.demo.entity;

// import jakarta.persistence.*;
// import lombok.*;

// import java.math.BigDecimal;
// import java.time.LocalDateTime;


// @Entity
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder

// public class BreachReport {

//     @Id
//     @GeneratedValue
//     private Long id;

//     @ManyToOne
//     private Contract contract;

//     private Integer daysDelayed;
//     private BigDecimal penaltyAmount;
//     private LocalDateTime generatedAt;

//     @PrePersist
//     void onGen() {
//         generatedAt = LocalDateTime.now();
//     }
// }


package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "breach_reports")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreachReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;
    
    @Column(nullable = false)
    private Integer daysDelayed;
    
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal penaltyAmount;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime generatedAt = LocalDateTime.now();
}