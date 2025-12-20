package com.example.demo.entity;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class ContractEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String contractNumber;
    private String title;
    private String counterpartyName;
    private Date agreedDeliveryDate;
    @min(0)
    private BigDecimal baseContractValue;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void setId(Long id){
        this.id=id;
    }
    public Long getId(){
        return id;
    }
    public void setContractNumber(String contractNumber){
        this.contractNumber=contractNumber;
    }
    public String getContractNumber(){
        return contractNumber;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getTitle(){
        return title;
    }
    public void setCounterpartyName(String counterpartyName){
        this.counterpartyName=counterpartyName;
    }
    public  String getCounterpartyName(){
        return counterpartyName;
    }
    public void setAgreedDeliveryDate(Date agreedDeliveryDate){
        this.agreedDeliveryDate=agreedDeliveryDate;
    }
    public  Date getAgreedDeliveryDate(){
        return agreedDeliveryDate;
    }
    public void setBaseContractValue(BigDecimal baseContractValue){
        this.baseContractValue = baseContractValue;
    }
    public BigDecimal getBaseContractValue(){
        return baseContractValue;
    }
    public void setStatus(String status){
        this.status=status;
    }
    public String getStatus(){
        return status;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt){
        this.updatedAt=updatedAt;
    }
    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }

    public ContractEntity(Long id,String contractNumber,String title,
    String counterpartyName,Date agreedDeliveryDate,
    BigDecimal baseContractValue,String status,LocalDateTime createdAt,
    LocalDateTime updatedAt){
        this.id=id;
        this.title=title;
        this.contractNumber=contractNumber;
        this.counterpartyName=counterpartyName;
        this.agreedDeliveryDate=agreedDeliveryDate;
        this.baseContractValue=baseContractValue;
        this.status=status;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
    }
    public ContractEntity(){

    }
}
