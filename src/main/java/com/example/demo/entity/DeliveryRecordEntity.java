package com.example.demo.entity;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Present;
import jakarta.validation.constraints.Past;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class DeliveryRecordEntity{
     @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ContractEntity contract;
    @PastOrPresent
    private Date deliveryDate;
    private String notes;
    private LocalDateTime createdAt;

    public void setId(Long id){
        this.id=id;
    }
    public Long getId(){
        return id;
    }
    public void setContract(ContractEntity contract){
        this.contract=contract;
    }
    public ContractEntity getContract(){
        return contract;
    }
    public void setDeliveryDate(Date deliveryDate){
        this.deliveryDate=deliveryDate;
    }
    public  Date getDeliveryDate(){
        return deliveryDate;
    }
    public void setNotes(String notes){
        this.notes=notes;
    }
    public String getNotes(){
        return notes;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public DeliveryRecordEntity(Long id, ContractEntity contract, Date deliveryDate,
        String notes, LocalDateTime createdAt){

        this.id=id;
        this.contract=contract;
        this.deliveryDate=deliveryDate;
        this.notes=notes;
        this.createdAt=createdAt;
    }
    public  DeliveryRecordEntity(){
    }
}