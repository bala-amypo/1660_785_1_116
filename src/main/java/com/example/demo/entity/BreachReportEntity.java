package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
public class BreachRepositoryEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Contract contract;
    private LocalTimeDate reportGeneratedAt;
    private Integer daysDelayed;
    private BigDecimal penaltyAmount;
    private String remarks;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Contract getContract() {
        return contract;
    }
    public void setContract(Contract contract) {
        this.contract = contract;
    }
    public LocalTimeDate getReportGeneratedAt() {
        return reportGeneratedAt;
    }
    public void setReportGeneratedAt(LocalTimeDate reportGeneratedAt) {
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
    public BreachRepositoryEntity(Long id, Contract contract, LocalTimeDate reportGeneratedAt, Integer daysDelayed,
            BigDecimal penaltyAmount, String remarks) {
        this.id = id;
        this.contract = contract;
        this.reportGeneratedAt = reportGeneratedAt;
        this.daysDelayed = daysDelayed;
        this.penaltyAmount = penaltyAmount;
        this.remarks = remarks;
    }
    public BreachRepositoryEntity() {
    }

}