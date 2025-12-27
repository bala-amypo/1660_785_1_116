// package com.example.demo.entity;

// import jakarta.persistence.*;
// import lombok.*;

// import java.math.BigDecimal;

// @Entity
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class BreachRule {

//     @Id
//     @GeneratedValue
//     private Long id;

//     @Column(unique = true)
//     private String ruleName;

//     private BigDecimal penaltyPerDay;
//     private Double maxPenaltyPercentage;
//     private Boolean active = true;
//     private Boolean isDefaultRule = false;
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
@Table(name = "breach_rules")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreachRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String ruleName;
    
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal penaltyPerDay;
    
    @Column(nullable = false)
    private Double maxPenaltyPercentage;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    @Column(nullable = false)
    private Boolean isDefaultRule = false;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}