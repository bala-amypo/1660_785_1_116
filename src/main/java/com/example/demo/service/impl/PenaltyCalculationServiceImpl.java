package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final PenaltyCalculationRepository penaltyRepo;
    private final ContractRepository contractRepo;
    private final DeliveryRecordRepository deliveryRepo;
    private final BreachRuleRepository ruleRepo;

    public PenaltyCalculationServiceImpl(
            PenaltyCalculationRepository penaltyRepo,
            ContractRepository contractRepo,
            DeliveryRecordRepository deliveryRepo,
            BreachRuleRepository ruleRepo) {
        this.penaltyRepo = penaltyRepo;
        this.contractRepo = contractRepo;
        this.deliveryRepo = deliveryRepo;
        this.ruleRepo = ruleRepo;
    }

    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {

        Contract contract = contractRepo.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        DeliveryRecord record = deliveryRepo
                .findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("No delivery record"));

        BreachRule rule = ruleRepo
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new ResourceNotFoundException("No active breach rule"));

        long days = Math.max(0,
                ChronoUnit.DAYS.between(contract.getAgreedDeliveryDate(), record.getDeliveryDate()));

        BigDecimal perDay = rule.getPenaltyPerDay().multiply(BigDecimal.valueOf(days));
        BigDecimal maxCap = contract.getBaseContractValue()
                .multiply(BigDecimal.valueOf(rule.getMaxPenaltyPercentage() / 100));

        BigDecimal penalty = perDay.min(maxCap);

        return penaltyRepo.save(
                PenaltyCalculation.builder()
                        .contract(contract)
                        .appliedRule(rule)
                        .daysDelayed((int) days)
                        .calculatedPenalty(penalty)
                        .build()
        );
    }

    @Override
    public PenaltyCalculation getCalculationById(Long id) {
        return penaltyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calculation not found"));
    }

    @Override
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return penaltyRepo.findByContractId(contractId);
    }
}
