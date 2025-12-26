package com.example.demo.controller;

import com.example.demo.entity.BreachReport;
import com.example.demo.service.impl.BreachReportServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breach-reports")
public class BreachReportController {

    private final BreachReportServiceImpl service;

    public BreachReportController(BreachReportServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/contract/{contractId}")
    public BreachReport generate(@PathVariable Long contractId) {
        return service.generateReport(contractId);
    }

    @GetMapping("/contract/{contractId}")
    public List<BreachReport> list(@PathVariable Long contractId) {
        return service.getReportsForContract(contractId);
    }

    @GetMapping
    public List<BreachReport> getAll() {
        return service.getAllReports();
    }
}
