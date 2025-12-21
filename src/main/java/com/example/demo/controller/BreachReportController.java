package com.example.demo.controller;

import com.example.demo.entity.BreachReport;
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
    public BreachReport generateReport(
            @PathVariable Long contractId) {
        return reportService.generateReport(contractId);
    }

    @GetMapping("/{id}")
    public BreachReport getReportById(
            @PathVariable Long id) {
        return reportService.getReportById(id);
    }

    
    @GetMapping("/contract/{contractId}")
    public List<BreachReport> getReportsByContract(
            @PathVariable Long contractId) {
        return reportService.getReportsForContract(contractId);
    }

    @GetMapping
    public List<BreachReport> getAllReports() {
        return reportService.getAllReports();
    }
}
