package com.example.demo.entity;

public class ContractEntity{
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
    public  getCounterpartyName(){
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
    public String getCreatedAt(){
        return createdAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt){
        this.updatedAt=updatedAt;
    }
    public String getUpdatedAt(){
        return updatedAt;
}