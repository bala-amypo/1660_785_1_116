package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import jakarta.persistence.ManyToOne;

@Entity
public class BreachReportEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ContractEntity contract;
    private LocalDateTime reportGeneratedAt;
    private Integer daysDelayed;
    private BigDecimal penaltyAmount;
    private String remarks;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public ContractEntity getContract() {
        return contract;
    }
    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }
    public LocalDateTime getReportGeneratedAt() {
        return reportGeneratedAt;
    }
    public void setReportGeneratedAt(LocalDateTime reportGeneratedAt) {
        this.reportGeneratedAt = reportGeneratedAt;
    }
    public Integer getDaysDelayed() {
        return daysDelayed;
    }
    public void setDaysDelayed(Integer daysDelayed) {
        this.daysDelayed = daysDelayed;
    }
    public BigDecimal getPenaltyAmount() {
        return penaltyAmount;
    }
    public void setPenaltyAmount(BigDecimal penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public BreachReportEntity(Long id, Contract contract, LocalDateTime reportGeneratedAt, Integer daysDelayed,
            BigDecimal penaltyAmount, String remarks) {
        this.id = id;
        this.contract = contract;
        this.reportGeneratedAt = reportGeneratedAt;
        this.daysDelayed = daysDelayed;
        this.penaltyAmount = penaltyAmount;
        this.remarks = remarks;
    }
    public BreachReportEntity() {
    }

}