// package com.example.demo.service;

// import com.example.demo.entity.BreachRule;
// import java.util.List;

// public interface BreachRuleService {

//     BreachRule createRule(BreachRule rule);

//     BreachRule getActiveDefaultOrFirst();

//     List<BreachRule> getAllRules();

//     void deactivateRule(Long id);
// }


package com.example.demo.service;

import com.example.demo.entity.BreachRule;

import java.util.List;

public interface BreachRuleService {
    BreachRule createRule(BreachRule rule);
    BreachRule getActiveDefaultOrFirst();
    void deactivateRule(Long id);
    List<BreachRule> getAllRules();
}