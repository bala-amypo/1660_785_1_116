package com.example.demo.controller;

import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calculations")
public class PenaltyCalculationController {

    private final PenaltyCalculationService service;

    public PenaltyCalculationController(PenaltyCalculationService service) {
        this.service = service;
    }

    @PostMapping("/contract/{id}")
    public PenaltyCalculation calculate(@PathVariable Long id) {
        return service.calculatePenalty(id);
    }

    @GetMapping("/{id}")
    public PenaltyCalculation get(@PathVariable Long id) {
        return service.getCalculationById(id);
    }

    @GetMapping("/contract/{id}")
    public List<PenaltyCalculation> byContract(@PathVariable Long id) {
        return service.getCalculationsForContract(id);
    }
}


