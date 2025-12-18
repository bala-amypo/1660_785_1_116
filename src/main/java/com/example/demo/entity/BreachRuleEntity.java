package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
@Entity

public class BreachRuleEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String ruleName;
    private BigDecimal penaltyPerDay;
    private Double maxPenaltyPercentage;
    private Boolean active;
    private Boolean isDefaultRule;

    public void setId(Long id){
        this.id=id;
    }
    public Long getId(){
        return id;
    }
    public void setRuleName(String ruleName){
        this.ruleName=ruleName;
    }
    public String getruleName(){
        return ruleName;
    }
    public void SetDeliveryDate(Date deliveryDate){
        this.deliveryDate=deliveryDate;
    }
    public  Date DeliveryDate(){
        return deliveryDate;
    }
    public void setNotes(String notes){
        this.notes=notes;
    }
    public String getNotes(){
        return notes;
    }
    public void setcreatedAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public DeliveryRecord(Long id, Contract contract, Date deliveryDate,
        String notes, LocalDateTime createdAt){

        this.id=id;
        this.contract=contract;
        this.deliveryDate=deliveryDate;
        this.notes=notes;
        this.createdAt=createdAt;
    }
    public class DeliveryRecord(){
    }

}