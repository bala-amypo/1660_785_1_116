// package com.example.demo.entity;

// import jakarta.persistence.*;
// import lombok.*;

// import java.math.BigDecimal;
// import java.time.LocalDate;
// import java.time.LocalDateTime;


// @Entity
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder

// public class Contract {

//     @Id
//     @GeneratedValue
//     private Long id;

//     @Column(unique = true)
//     private String contractNumber;

//     private String title;
//     private String counterpartyName;
//     private LocalDate agreedDeliveryDate;
//     private BigDecimal baseContractValue;
//     private String status = "ACTIVE";
//     private LocalDateTime createdAt;

//     @PrePersist
//     void onCreate() {
//         createdAt = LocalDateTime.now();
//     }
// }


package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "contracts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String contractNumber;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String counterpartyName;
    
    @Column(nullable = false)
    private LocalDate agreedDeliveryDate;
    
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal baseContractValue;
    
    @Column(nullable = false)
    private String status;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}