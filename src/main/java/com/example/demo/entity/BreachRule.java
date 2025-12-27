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

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BreachRule {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String ruleName;

    @NotNull
    private BigDecimal penaltyPerDay;

    @NotNull
    private Double maxPenaltyPercentage;

    private Boolean active = true;
    private Boolean isDefaultRule = false;
}

