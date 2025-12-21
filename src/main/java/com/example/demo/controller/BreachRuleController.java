package com.example.demo.controller;

import com.example.demo.entity.BreachRule;
import com.example.demo.service.BreachRuleService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/breach-rules")
public class BreachRuleController {

    private BreachRuleService ruleService;

    public BreachRuleController(BreachRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public BreachRule create(@RequestBody BreachRule rule) {
        return ruleService.createRule(rule);
    }

    @PutMapping("/{id}")
    public BreachRule update(@PathVariable Long id,
                                   @RequestBody BreachRule rule) {
        return ruleService.updateRule(id, rule);
    }

    @GetMapping("/{id}")
    public BreachRule get(@PathVariable Long id) {
        return ruleService.getRuleById(id);
    }

    @GetMapping
    public List<BreachRule> getAll() {
        return ruleService.getAllRules();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        ruleService.deactivateRule(id);
    }
}
