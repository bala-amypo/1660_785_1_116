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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breach-rules")
public class BreachRuleController {
    
    @Autowired
    private BreachRuleService breachRuleService;
    
    @PostMapping
    public ResponseEntity<BreachRule> createRule(@RequestBody BreachRule rule) {
        BreachRule created = breachRuleService.createRule(rule);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/active-default")
    public ResponseEntity<BreachRule> getActiveDefaultRule() {
        BreachRule rule = breachRuleService.getActiveDefaultOrFirst();
        return ResponseEntity.ok(rule);
    }
    
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateRule(@PathVariable Long id) {
        breachRuleService.deactivateRule(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping
    public ResponseEntity<List<BreachRule>> getAllRules() {
        List<BreachRule> rules = breachRuleService.getAllRules();
        return ResponseEntity.ok(rules);
    }
}