package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class PenaltyCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Contract contract;

    @ManyToOne
    private BreachRule breachRule;

    private Integer daysDelayed;
    private BigDecimal calculatedPenalty;
    private LocalDateTime calculatedAt;

    public PenaltyCalculation() {
    }

    public Long getId() {
        return id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public BreachRule getBreachRule() {
        return breachRule;
    }

    public void setBreachRule(BreachRule breachRule) {
        this.breachRule = breachRule;
    }

    public Integer getDaysDelayed() {
        return daysDelayed;
    }

    public void setDaysDelayed(Integer daysDelayed) {
        this.daysDelayed = daysDelayed;
    }

    public BigDecimal getCalculatedPenalty() {
        return calculatedPenalty;
    }

    public void setCalculatedPenalty(BigDecimal calculatedPenalty) {
        this.calculatedPenalty = calculatedPenalty;
    }

    public LocalDateTime getCalculatedAt() {
        return calculatedAt;
    }

    public void setCalculatedAt(LocalDateTime calculatedAt) {
        this.calculatedAt = calculatedAt;
    }
}
