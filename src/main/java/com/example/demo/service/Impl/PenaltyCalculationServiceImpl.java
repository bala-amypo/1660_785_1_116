package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.entity.Contract;
import com.example.demo.entity.BreachRule;
import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.PenaltyCalculationService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

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
        public PenaltyCalculation save(PenaltyCalculation p) {
        p.setCalculatedAt(LocalDateTime.now());
        BreachRule rule = ruleRepo.findFirstByActiveTrueOrderByIsDefaultRuleDesc();
        p.setBreachRule(rule);
        return penaltyRepo.save(p);
    }

    
    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {
        Contract contract = contractRepo.findById(contractId).orElse(null);
        if (contract == null) return null;

        BreachRule rule = ruleRepo.findFirstByActiveTrueOrderByIsDefaultRuleDesc();

        long days = LocalDate.now().toEpochDay()
                - contract.getAgreedDeliveryDate().toEpochDay();
        if (days < 0) days = 0;

        BigDecimal penalty =
                rule.getPenaltyPerDay().multiply(BigDecimal.valueOf(days));

        BigDecimal maxPenalty =
                contract.getBaseContractValue()
                        .multiply(BigDecimal.valueOf(rule.getMaxPenaltyPercentage() / 100));

        if (penalty.compareTo(maxPenalty) > 0) penalty = maxPenalty;

        PenaltyCalculation calc = new PenaltyCalculation();
        calc.setContract(contract);
        calc.setDaysDelayed((int) days);
        calc.setCalculatedPenalty(penalty);

        return penaltyRepo.save(calc);
    }

    @Override
    public PenaltyCalculation getCalculationById(Long id) {
        return penaltyRepo.findById(id).orElse(null);
    }

    @Override
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return penaltyRepo.findByContractId(contractId);
    }
}

