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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breach-reports")
public class BreachReportController {
    
    @Autowired
    private BreachReportService breachReportService;
    
    @PostMapping("/generate/{contractId}")
    public ResponseEntity<BreachReport> generateReport(@PathVariable Long contractId) {
        BreachReport report = breachReportService.generateReport(contractId);
        return ResponseEntity.ok(report);
    }
    
    @GetMapping("/contract/{contractId}")
    public ResponseEntity<List<BreachReport>> getReportsForContract(@PathVariable Long contractId) {
        List<BreachReport> reports = breachReportService.getReportsForContract(contractId);
        return ResponseEntity.ok(reports);
    }
    
    @GetMapping
    public ResponseEntity<List<BreachReport>> getAllReports() {
        List<BreachReport> reports = breachReportService.getAllReports();
        return ResponseEntity.ok(reports);
    }
}