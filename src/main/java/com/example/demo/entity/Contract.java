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

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String contractNumber;

    private String title;
    private String counterpartyName;

    private LocalDate agreedDeliveryDate;

    private BigDecimal baseContractValue;

    private String status; // ACTIVE, COMPLETED, BREACHED

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
