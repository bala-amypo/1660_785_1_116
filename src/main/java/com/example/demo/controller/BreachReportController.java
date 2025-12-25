package com.example.demo.controller;

import com.example.demo.entity.BreachReport;
import com.example.demo.service.BreachReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class BreachReportController {

    private final BreachReportService service;

    public BreachReportController(BreachReportService service) {
        this.service = service;
    }

    @PostMapping("/generate/{contractId}")
    public BreachReport generate(@PathVariable Long contractId) {
        return service.generateReport(contractId);
    }

    @GetMapping("/{id}")
    public BreachReport get(@PathVariable Long id) {
        return service.getReportById(id);
    }

    @GetMapping("/contract/{contractId}")
    public List<BreachReport> listByContract(@PathVariable Long contractId) {
        return service.getReportsForContract(contractId);
    }

    @GetMapping
    public List<BreachReport> listAll() {
        return service.getAllReports();
    }
}
