// package com.example.demo.entity;

// import jakarta.persistence.*;
// import lombok.*;

// import java.time.LocalDate;



// @Entity
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder

// public class DeliveryRecord {

//     @Id
//     @GeneratedValue
//     private Long id;

//     @ManyToOne
//     private Contract contract;

//     private LocalDate deliveryDate;
//     private String notes;
// }


package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRecord {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Contract contract;

    private LocalDate deliveryDate;
    private String notes;

    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
