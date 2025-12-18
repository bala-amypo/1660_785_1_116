package com.example.demo.entity;

public class ContractEntity{
    private Long id;
    private String contractNumber;
    private String title;
    private String counterpartyName;
    private Date agreedDeliveryDate;
    private BigDecimal baseContractValue;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

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
        return id;
    }
    public void setCounterpartyName(String counterpartyName){
        this.counterpartyName=counterpartyName;
    }
    public  getCounterpartyName(){
        return counterpartyName;
    }
    public void set(Long id){
        this.id=id;
    }
    public Long getId(){
        return id;
    }   
}