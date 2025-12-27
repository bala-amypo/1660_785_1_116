package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //added
public class DeliveryRecord {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Contract contract;

    private LocalDate deliveryDate;
    private String notes;
}
