package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.entity.ContractEntity;
import com.example.demo.entity.BreachRuleEntity;
import com.example.demo.entity.PenaltyCalculationEntity;

import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.repository.PenaltyCalculationRepository;

import com.example.demo.service.PenaltyCalculationService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PenaltyCalculationServiceImpl
        implements PenaltyCalculationService {

    private PenaltyCalculationRepository penaltyRepo;
    private ContractRepository contractRepo;
    private BreachRuleRepository ruleRepo;

    public PenaltyCalculationServiceImpl(
            PenaltyCalculationRepository penaltyRepo,
            ContractRepository contractRepo,
            BreachRuleRepository ruleRepo) {
        this.penaltyRepo = penaltyRepo;
        this.contractRepo = contractRepo;
        this.ruleRepo = ruleRepo;
    }

    @Override
    public PenaltyCalculationEntity calculatePenalty(Long contractId) {

        ContractEntity contract =
                contractRepo.findById(contractId).orElse(null);

        if (contract == null) {
            return null;
        }

        BreachRuleEntity rule =
                ruleRepo.findFirstByActiveTrueOrderByIsDefaultRuleDesc();

        long days =
                LocalDate.now().toEpochDay()
                        - contract.getAgreedDeliveryDate().toEpochDay();

        if (days < 0) {
            days = 0;
        }

        BigDecimal penalty =
                rule.getPenaltyPerDay()
                        .multiply(BigDecimal.valueOf(days));

        BigDecimal maxPenalty =
                contract.getBaseContractValue()
                        .multiply(BigDecimal.valueOf(
                                rule.getMaxPenaltyPercentage() / 100));

        if (penalty.compareTo(maxPenalty) > 0) {
            penalty = maxPenalty;
        }

        PenaltyCalculationEntity calc =
                new PenaltyCalculationEntity();

        calc.setContract(contract);
        calc.setBreachRule(rule);
        calc.setDaysDelayed((int) days);
        calc.setCalculatedPenalty(penalty);
        calc.setCalculatedAt(LocalDateTime.now());

        return penaltyRepo.save(calc);
    }

    @Override
    public PenaltyCalculationEntity getCalculationById(Long id) {
        return penaltyRepo.findById(id).orElse(null);
    }

    @Override
    public List<PenaltyCalculationEntity>
    getCalculationsForContract(Long contractId) {
        return penaltyRepo.findByContractId(contractId);
    }
}
