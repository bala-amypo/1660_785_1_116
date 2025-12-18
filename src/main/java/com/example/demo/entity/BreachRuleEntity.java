package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Max;
@Entity

public class BreachRuleEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String ruleName;
    private BigDecimal penaltyPerDay;
    @Size(min=0,max=100)
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
    public void setRuleName(String ruleName){
        this.ruleName=ruleName;
    }
    public String getruleName(){
        return ruleName;
    }
    public void setPenaltyPerDay(BigDecimal penaltyPerDay){
        this.penaltyPerDay=penaltyPerDay;
    }
    public BigDecimal getPenaltyPerDay(){
        return penaltyPerDay;
    }
    public void setMaxPenaltyPercentage(Double maxPenaltyPercentage){
        this.maxPenaltyPercentage=maxPenaltyPercentage;
    }
    public Double getMaxPenaltyPercentage(){
        return maxPenaltyPercentage;
    }
    public void setActive(Boolean active){
        this.active=active;
    }
    public Boolean getactive(){
        return active;
    }
    public void setIsDefaultRule(Boolean isDefaultRule){
        this.isDefaultRule=isDefaultRule;
    }
    public Boolean getisDefaultRule(){
        return isDefaultRule;
    }

    public BreachRule(Long id,String ruleName,BigDecimal penaltyPerDay,
        Double maxPenaltyPercentage,Boolean active,Boolean isDefaultRule){
           
        this.id=id;
        this.ruleName=ruleName;
        this.ruleName=ruleName;
        this.penaltyPerDay=penaltyPerDay;
        this.maxPenaltyPercentage=maxPenaltyPercentage;
        this.active=active;
        this.isDefaultRule=isDefaultRule;
        }
        public BreachRule(){

        }
           
}