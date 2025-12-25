package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;


@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    private final BreachRuleRepository ruleRepo;

    public BreachRuleServiceImpl(BreachRuleRepository ruleRepo) {
        this.ruleRepo = ruleRepo;
    }

    @Override
    public BreachRule createRule(BreachRule rule) {
        if (rule.getPenaltyPerDay().doubleValue() <= 0) {
            throw new BadRequestException("Penalty per day must be greater than zero");
        }
        if (rule.getMaxPenaltyPercentage() < 0 ||
                rule.getMaxPenaltyPercentage() > 100) {
            throw new BadRequestException("Max penalty percentage invalid");
        }
        return ruleRepo.save(rule);
    }

    @Override
    public BreachRule updateRule(Long id, BreachRule updated) {
        BreachRule rule = getRuleById(id);
        rule.setPenaltyPerDay(updated.getPenaltyPerDay());
        rule.setMaxPenaltyPercentage(updated.getMaxPenaltyPercentage());
        return ruleRepo.save(rule);
    }

    @Override
    public BreachRule getRuleById(Long id) {
        return ruleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
    }

    @Override
    public List<BreachRule> getAllRules() {
        return ruleRepo.findAll();
    }

    @Override
    public void deactivateRule(Long id) {
        BreachRule rule = getRuleById(id);
        rule.setActive(false);
        ruleRepo.save(rule);
    }

    @Override
    public BreachRule getActiveDefaultOrFirst() {
        return ruleRepo.findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() ->
                        new ResourceNotFoundException("No active breach rule"));
    }
}
