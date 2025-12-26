package com.example.demo.controller;

import com.example.demo.entity.BreachReport;
import com.example.demo.service.impl.BreachReportServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class BreachReportController {

    private final BreachReportServiceImpl service;

    public BreachReportController(BreachReportServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/generate/{contractId}")
    public BreachReport generateReport(@PathVariable Long contractId) {
        return service.generateReport(contractId);
    }

    @GetMapping("/{id}")
    public BreachReport getReportById(@PathVariable Long id) {
        return service.getAllReports()
                .stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Report not found"));
    }

    @GetMapping("/contract/{contractId}")
    public List<BreachReport> getReportsByContract(@PathVariable Long contractId) {
        return service.getReportsForContract(contractId);
    }

    @GetMapping
    public List<BreachReport> getAllReports() {
        return service.getAllReports();
    }
}
