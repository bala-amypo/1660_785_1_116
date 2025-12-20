package com.example.demo.service;

import com.example.demo.entity.BreachRuleEntity;
import java.util.List;

public interface BreachRuleService {

    BreachRuleEntity createRule(BreachRuleEntity rule);

    BreachRuleEntity updateRule(Long id, BreachRuleEntity rule);

    BreachRuleEntity getRuleById(Long id);

    List<BreachRuleEntity> getAllRules();

    void deactivateRule(Long id);

    BreachRuleEntity getActiveDefaultOrFirst();
}
