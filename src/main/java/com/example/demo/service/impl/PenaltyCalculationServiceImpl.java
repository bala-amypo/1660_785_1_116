package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PenaltyCalculationServiceImpl {

    ContractRepository contractRepository;
    DeliveryRecordRepository deliveryRecordRepository;
    BreachRuleRepository breachRuleRepository;
    PenaltyCalculationRepository penaltyCalculationRepository;

    public PenaltyCalculation calculatePenalty(Long contractId) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("not found"));

        DeliveryRecord record = deliveryRecordRepository
                .findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElseThrow(() ->
                        new RuntimeException("No delivery record"));

        BreachRule rule = breachRuleRepository
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() ->
                        new RuntimeException("No active breach rule"));

        long daysDelayed =
                Math.max(0,
                        record.getDeliveryDate().toEpochDay()
                                - contract.getAgreedDeliveryDate().toEpochDay());

        BigDecimal calculated =
                rule.getPenaltyPerDay()
                        .multiply(BigDecimal.valueOf(daysDelayed));

        BigDecimal maxAllowed =
                contract.getBaseContractValue()
                        .multiply(BigDecimal.valueOf(rule.getMaxPenaltyPercentage()))
                        .divide(BigDecimal.valueOf(100));

        if (calculated.compareTo(maxAllowed) > 0) {
            calculated = maxAllowed;
        }
        return penaltyCalculationRepository.save(
                PenaltyCalculation.builder()
                        .contract(contract)
                        .daysDelayed((int) daysDelayed)
                        .calculatedPenalty(calculated)
                        .build()
        );
    }
    public PenaltyCalculation getCalculationById(Long id) {
        return penaltyCalculationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Calculation not found"));
    }
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return penaltyCalculationRepository.findByContractId(contractId);
    }
}
