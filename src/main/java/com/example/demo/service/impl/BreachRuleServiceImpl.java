package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.entity.BreachRule;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;

import java.util.List;

@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    private BreachRuleRepository ruleRepo;

    public BreachRuleServiceImpl(BreachRuleRepository ruleRepo) {
        this.ruleRepo = ruleRepo;
    }

    @Override
    public BreachRule createRule(BreachRule rule) {
        rule.setActive(true);
        return ruleRepo.save(rule);
    }

    @Override
    public BreachRule updateRule(Long id, BreachRule rule) {
        BreachRule existing = ruleRepo.findById(id).orElse(null);
        if (existing != null) {
            existing.setPenaltyPerDay(rule.getPenaltyPerDay());
            existing.setMaxPenaltyPercentage(rule.getMaxPenaltyPercentage());
            return ruleRepo.save(existing);
        }
        return null;
    }

    @Override
    public BreachRule getRuleById(Long id) {
        return ruleRepo.findById(id).orElse(null);
    }

    @Override
    public List<BreachRule> getAllRules() {
        return ruleRepo.findAll();
    }

    @Override
    public void deactivateRule(Long id) {
        BreachRule rule = ruleRepo.findById(id).orElse(null);
        if (rule != null) {
            rule.setActive(false);
            ruleRepo.save(rule);
        }
    }

    @Override
    public BreachRule getActiveDefaultOrFirst() {
        return ruleRepo.findFirstByActiveTrueOrderByIsDefaultRuleDesc();
    }
}
