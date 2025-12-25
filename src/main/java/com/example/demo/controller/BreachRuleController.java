package com.example.demo.controller;

import com.example.demo.entity.BreachRule;
import com.example.demo.service.BreachRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breach-rules")
public class BreachRuleController {

    private final BreachRuleService service;

    public BreachRuleController(BreachRuleService service) {
        this.service = service;
    }

    @PostMapping
    public BreachRule create(@RequestBody BreachRule r) {
        return service.createRule(r);
    }

    @PutMapping("/{id}")
    public BreachRule update(@PathVariable Long id,
                             @RequestBody BreachRule r) {
        return service.updateRule(id, r);
    }

    @GetMapping("/{id}")
    public BreachRule get(@PathVariable Long id) {
        return service.getRuleById(id);
    }

    @GetMapping
    public List<BreachRule> list() {
        return service.getAllRules();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivateRule(id);
    }
}
