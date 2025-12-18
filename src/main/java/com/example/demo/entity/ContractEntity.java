package com.example.demo.entity;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
@Entity
public class ContractEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String contractNumber;
    private String title;
    private String counterpartyName;
    private Date agreedDeliveryDate;
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
    public void SetAgreedDeliveryDate(String agreedDeliveryDate){
        this.agreedDeliveryDate=agreedDeliveryDate;
    }
    public  Date getAgreedDeliveryDate(){
        return agreedDeliveryDate;
    }
    public void setBaseContract(BigDecimal title){
        this.basecontract=basecontract;
    }
    public BigDecimal getbasecontract(){
        return basecontract;
    }
    public void setStatus(String status){
        this.status=status;
    }
    public String getStatus(){
        return status;
    }
    public void setcreatedAt(LocalDateTime createdAt){
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
        this.contractNumber=contractNumber;
        this.counterpartyName=counterpartyName;
        this.agreedDeliveryDate=agreedDeliveryDate;
        this.basecontract=basecontract;
        this.status=status;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
    }
    public ContractEntity(){

    }
}
