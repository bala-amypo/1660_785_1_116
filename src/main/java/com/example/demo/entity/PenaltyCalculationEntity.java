package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import jakarta.validation.constraints.Min;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import jakarta.persistence.OneToOne;
@Entity

public class PenaltyCalculationEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Contract contract;
    @Min(0)
    private Integer daysDelayed;
    @BigDecimal
    private BigDecimal calculatedPenalty;
    @ManyToOne
    private BreachRuleEntity appliedRule;
    private LocalDateTime calculatedAt;

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
        return contract;
    }
    public void setDaysDelayed(Integer daysDelayed){
        this.daysDelayed=daysDelayed;
    }
    public  Integer getDaysDelayed(){
        return daysDelayed;
    }
    public void setCalculatedPenalty(BigDecimal calculatedPenalty){
        this.calculatedPenalty=calculatedPenalty;
    }
    public BigDecimal getCalculatedPenalty(){
        return calculatedPenalty;
    }
    public void setAppliedRule(BreachRule appliedRule){
        this.appliedRule=appliedRule;
    }
    public BreachRule getAppliedRule(){
        return appliedRule;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public PenaltyCalculationEntity(Long id, Contract contract,Integer daysDelayed,
    BigDecimal calculatedPenalty,BreachRule appliedRule,
    LocalDateTime createdAt){

        this.id=id;
        this.contract=contract;
        this.daysDelayed=daysDelayed;
        this.calculatedPenalty=calculatedPenalty;
        this.appliedRule=appliedRule;
        this.createdAt=createdAt;
    }
    public PenaltyCalculationEntity(){

    }
}