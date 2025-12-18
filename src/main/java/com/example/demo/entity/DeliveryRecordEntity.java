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
    public String getContract(){
        return contractNumber;
    }
    public void SetDeliveryDate(Date DeliveryDate){
        this.agreDeliveryDate=agreedDeliveryDate;
    }
    public  Date getAgreedDeliveryDate(){
        return agreedDeliveryDate;
    }
    public void setNotes(String notes){
        this.notes=notes;
    }
    public String getNotes(){
        return notes;
    }
}