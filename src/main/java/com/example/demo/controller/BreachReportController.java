package com.example.demo.controller;

import com.example.demo.entity.BreachReportEntity;
import com.example.demo.service.BreachReportService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/breach-reports")
public class BreachReportController {

    private BreachReportService reportService;

    public BreachReportController(BreachReportService reportService) {
        this.reportService = reportService;
    }
    @PostMapping("/contract/{contractId}")
    public BreachReportEntity generateReport(
            @PathVariable Long contractId) {
        return reportService.generateReport(contractId);
    }

    @GetMapping("/{id}")
    public BreachReportEntity getReportById(
            @PathVariable Long id) {
        return reportService.getReportById(id);
    }

    
    @GetMapping("/contract/{contractId}")
    public List<BreachReportEntity> getReportsByContract(
            @PathVariable Long contractId) {
        return reportService.getReportsForContract(contractId);
    }

    @GetMapping
    public List<BreachReportEntity> getAllReports() {
        return reportService.getAllReports();
    }
}
