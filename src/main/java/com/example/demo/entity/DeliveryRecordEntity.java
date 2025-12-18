package com.example.demo.entity;

import java.util.Date;
import jakarta.persistence.Id;
@Entity
public class DeliveryRecordEntity{
     @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Contract contract;
    private Date deliveryDate;
    private String notes;
    private LocalDateTime createdAt;

    public void setId(Long id){
        this.id=id;
    }
    public Long getId(){
        return id;
    }
    public void setContract(Contract contract){
        this.contract=contract;
    }
    public Contract getContract(){
        return contrac;
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

    public class DeliveryRecord(Long id,Contract contract;
    private Date deliveryDate;
    private String notes;
    private LocalDateTime createdAt;
    )
}