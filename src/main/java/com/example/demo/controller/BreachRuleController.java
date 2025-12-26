package com.example.demo.controller;

import com.example.demo.entity.BreachRule;
import com.example.demo.service.impl.BreachRuleServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breach-rules")
public class BreachRuleController {

    private final BreachRuleServiceImpl service;

    public BreachRuleController(BreachRuleServiceImpl service) {
        this.service = service;
    }

    // POST /api/breach-rules
    @PostMapping
    public BreachRule createRule(@RequestBody BreachRule rule) {
        return service.createRule(rule);
    }

    // PUT /api/breach-rules/{id}
    @PutMapping("/{id}")
    public BreachRule updateRule(@PathVariable Long id,
                                 @RequestBody BreachRule rule) {
        rule.setId(id);
        return service.createRule(rule); // simple update logic
    }

    // GET /api/breach-rules/{id}
    @GetMapping("/{id}")
    public BreachRule getRuleById(@PathVariable Long id) {
        return service.getAllRules()
                .stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Rule not found"));
    }

    // GET /api/breach-rules
    @GetMapping
    public List<BreachRule> getAllRules() {
        return service.getAllRules();
    }

    // PUT /api/breach-rules/{id}/deactivate
    @PutMapping("/{id}/deactivate")
    public void deactivateRule(@PathVariable Long id) {
        service.deactivateRule(id);
    }
}
