// package com.example.demo.service.impl;

// import com.example.demo.entity.BreachRule;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.repository.BreachRuleRepository;
// import com.example.demo.service.BreachRuleService;
// import org.springframework.stereotype.Service;

// import java.math.BigDecimal;
// import java.util.List;

// @Service
// public class BreachRuleServiceImpl implements BreachRuleService {

//     BreachRuleRepository breachRuleRepository;

//     @Override
//     public BreachRule createRule(BreachRule rule) {
//         if (rule.getPenaltyPerDay().compareTo(BigDecimal.ZERO) <= 0)
//             throw new IllegalArgumentException("penalty");

//         if (rule.getMaxPenaltyPercentage() > 100)
//             throw new IllegalArgumentException("percent");

//         return breachRuleRepository.save(rule);
//     }

//     @Override
//     public BreachRule getActiveDefaultOrFirst() {
//         return breachRuleRepository
//                 .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
//                 .orElseThrow(() -> new ResourceNotFoundException("No active breach rule"));
//     }

//     @Override
//     public List<BreachRule> getAllRules() {
//         return breachRuleRepository.findAll();
//     }

//     @Override
//     public void deactivateRule(Long id) {
//         BreachRule r = breachRuleRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
//         r.setActive(false);
//         breachRuleRepository.save(r);
//     }
// }


package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    private final BreachRuleRepository breachRuleRepository;

    public BreachRuleServiceImpl(BreachRuleRepository breachRuleRepository) {
        this.breachRuleRepository = breachRuleRepository;
    }

    @Override
    public BreachRule createRule(BreachRule rule) {

        if (rule.getPenaltyPerDay().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Penalty per day must be > 0");
        }

        if (rule.getMaxPenaltyPercentage() < 0 ||
            rule.getMaxPenaltyPercentage() > 100) {
            throw new IllegalArgumentException("Invalid penalty percentage");
        }

        return breachRuleRepository.save(rule);
    }

    @Override
    public BreachRule updateRule(Long id, BreachRule rule) {
        BreachRule existing = getRuleById(id);
        existing.setPenaltyPerDay(rule.getPenaltyPerDay());
        existing.setMaxPenaltyPercentage(rule.getMaxPenaltyPercentage());
        existing.setActive(rule.getActive());
        existing.setIsDefaultRule(rule.getIsDefaultRule());
        return breachRuleRepository.save(existing);
    }

    @Override
    public List<BreachRule> getAllRules() {
        return breachRuleRepository.findAll();
    }

    @Override
    public BreachRule getRuleById(Long id) {
        return breachRuleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Rule not found"));
    }

    @Override
    public void deactivateRule(Long id) {
        BreachRule rule = getRuleById(id);
        rule.setActive(false);
        breachRuleRepository.save(rule);
    }

    @Override
    public BreachRule getActiveDefaultOrFirst() {
        return breachRuleRepository
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() ->
                        new ResourceNotFoundException("No active breach rule"));
    }
}
