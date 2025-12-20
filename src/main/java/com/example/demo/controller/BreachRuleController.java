package com.example.demo.controller;

import com.example.demo.entity.BreachRuleEntity;
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
    public BreachRuleEntity create(@RequestBody BreachRuleEntity rule) {
        return ruleService.createRule(rule);
    }

    @PutMapping("/{id}")
    public BreachRuleEntity update(@PathVariable Long id,
                                   @RequestBody BreachRuleEntity rule) {
        return ruleService.updateRule(id, rule);
    }

    @GetMapping("/{id}")
    public BreachRuleEntity get(@PathVariable Long id) {
        return ruleService.getRuleById(id);
    }

    @GetMapping
    public List<BreachRuleEntity> getAll() {
        return ruleService.getAllRules();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        ruleService.deactivateRule(id);
    }
}
