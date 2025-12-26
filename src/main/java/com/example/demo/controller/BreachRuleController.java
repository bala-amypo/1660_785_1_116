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

    @PostMapping
    public BreachRule create(@RequestBody BreachRule r) {
        return service.createRule(r);
    }

    @GetMapping
    public List<BreachRule> getAll() {
        return service.getAllRules();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivateRule(id);
    }
}
