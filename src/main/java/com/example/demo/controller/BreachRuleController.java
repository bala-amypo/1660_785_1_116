// package com.example.demo.controller;

// import com.example.demo.entity.BreachRule;
// import com.example.demo.service.BreachRuleService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/breach-rules")
// public class BreachRuleController {

//     private final BreachRuleService service;

//     public BreachRuleController(BreachRuleService service) {
//         this.service = service;
//     }

//     @PostMapping
//     public BreachRule create(@RequestBody BreachRule r) {
//         return service.createRule(r);
//     }

//     @GetMapping
//     public List<BreachRule> getAll() {
//         return service.getAllRules();
//     }

//     @PutMapping("/{id}/deactivate")
//     public void deactivate(@PathVariable Long id) {
//         service.deactivateRule(id);
//     }
// }



package com.example.demo.controller;

import com.example.demo.entity.BreachRule;
import com.example.demo.service.BreachRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breach-rules")
public class BreachRuleController {

    private final BreachRuleService breachRuleService;

    public BreachRuleController(BreachRuleService breachRuleService) {
        this.breachRuleService = breachRuleService;
    }

    @PostMapping
    public BreachRule create(@RequestBody BreachRule rule) {
        return breachRuleService.createRule(rule);
    }

    @PutMapping("/{id}")
    public BreachRule update(@PathVariable Long id,
                             @RequestBody BreachRule rule) {
        return breachRuleService.updateRule(id, rule);
    }

    @GetMapping("/{id}")
    public BreachRule get(@PathVariable Long id) {
        return breachRuleService.getRuleById(id);
    }

    @GetMapping
    public List<BreachRule> getAll() {
        return breachRuleService.getAllRules();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        breachRuleService.deactivateRule(id);
    }
}
