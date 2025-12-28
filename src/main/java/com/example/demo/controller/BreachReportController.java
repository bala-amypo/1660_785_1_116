// package com.example.demo.controller;

// import com.example.demo.entity.BreachReport;
// import com.example.demo.service.BreachReportService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/breach-reports")
// public class BreachReportController {

//     private final BreachReportService service;

//     public BreachReportController(BreachReportService service) {
//         this.service = service;
//     }

//     @PostMapping("/contract/{id}")
//     public BreachReport generate(@PathVariable Long id) {
//         return service.generateReport(id);
//     }

//     @GetMapping("/contract/{id}")
//     public List<BreachReport> byContract(@PathVariable Long id) {
//         return service.getReportsForContract(id);
//     }

//     @GetMapping
//     public List<BreachReport> getAll() {
//         return service.getAllReports();
//     }
// }



package com.example.demo.controller;

import com.example.demo.entity.BreachReport;
import com.example.demo.service.BreachReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class BreachReportController {

    private final BreachReportService breachReportService;

    public BreachReportController(BreachReportService breachReportService) {
        this.breachReportService = breachReportService;
    }

    @PostMapping("/generate/{contractId}")
    public BreachReport generate(@PathVariable Long contractId) {
        return breachReportService.generateReport(contractId);
    }

    @GetMapping("/{id}")
    public BreachReport getById(@PathVariable Long id) {
        return breachReportService.getReportById(id);
    }

    @GetMapping("/contract/{contractId}")
    public List<BreachReport> getByContract(
            @PathVariable Long contractId) {
        return breachReportService.getReportsForContract(contractId);
    }

    @GetMapping
    public List<BreachReport> getAll() {
        return breachReportService.getAllReports();
    }
}
