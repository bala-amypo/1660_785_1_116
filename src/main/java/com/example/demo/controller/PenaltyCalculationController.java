package com.example.demo.controller;

import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.service.PenaltyCalculationService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/penalty-calculations")
public class PenaltyCalculationController {

    private PenaltyCalculationService penaltyService;

    public PenaltyCalculationController(
            PenaltyCalculationService penaltyService) {
        this.penaltyService = penaltyService;
    }

    @PostMapping("/contract/{contractId}")
    public PenaltyCalculation calculatePenalty(
            @PathVariable Long contractId) {
        return penaltyService.calculatePenalty(contractId);
    }

    @GetMapping("/{id}")
    public PenaltyCalculation getById(
            @PathVariable Long id) {
        return penaltyService.getCalculationById(id);
    }

   
    @GetMapping("/contract/{contractId}")
    public List<PenaltyCalculation> getByContract(
            @PathVariable Long contractId) {
        return penaltyService.getCalculationsForContract(contractId);
    }
}
