package com.example.demo.controller;

import com.example.demo.entity.BreachReport;
import com.example.demo.service.BreachReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breach-reports")
public class BreachReportController {

    private final BreachReportService service;

    public BreachReportController(BreachReportService service) {
        this.service = service;
    }

    @PostMapping("/contract/{id}")
    // public BreachReport generate(@PathVariable Long id) {
    //     return service.generateReport(id);
    // }
public BreachRule create(@Valid @RequestBody BreachRule rule) {
    return service.createRule(rule);

    @GetMapping("/contract/{id}")
    public List<BreachReport> byContract(@PathVariable Long id) {
        return service.getReportsForContract(id);
    }

    @GetMapping
    public List<BreachReport> getAll() {
        return service.getAllReports();
    }
}

