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

// public class PenaltyCalculation {

//     @Id
//     @GeneratedValue
//     private Long id;

//     @ManyToOne
//     private Contract contract;

//     private Integer daysDelayed;
//     private BigDecimal calculatedPenalty;
//     private LocalDateTime calculatedAt;

//     @PrePersist
//     void onCalc() {
//         calculatedAt = LocalDateTime.now();
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
@Table(name = "penalty_calculations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyCalculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;
    
    @Column(nullable = false)
    private Integer daysDelayed;
    
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal calculatedPenalty;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime calculatedAt = LocalDateTime.now();
}