// package com.example.demo.controller;

// import com.example.demo.entity.PenaltyCalculation;
// import com.example.demo.service.PenaltyCalculationService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/calculations")
// public class PenaltyCalculationController {

//     private final PenaltyCalculationService service;

//     public PenaltyCalculationController(PenaltyCalculationService service) {
//         this.service = service;
//     }

//     @PostMapping("/contract/{id}")
//     public PenaltyCalculation calculate(@PathVariable Long id) {
//         return service.calculatePenalty(id);
//     }

//     @GetMapping("/{id}")
//     public PenaltyCalculation get(@PathVariable Long id) {
//         return service.getCalculationById(id);
//     }

//     @GetMapping("/contract/{id}")
//     public List<PenaltyCalculation> byContract(@PathVariable Long id) {
//         return service.getCalculationsForContract(id);
//     }
// }


package com.example.demo.controller;

import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/penalty-calculations")
public class PenaltyCalculationController {
    
    @Autowired
    private PenaltyCalculationService penaltyCalculationService;
    
    @PostMapping("/calculate/{contractId}")
    public ResponseEntity<PenaltyCalculation> calculatePenalty(@PathVariable Long contractId) {
        PenaltyCalculation calculation = penaltyCalculationService.calculatePenalty(contractId);
        return ResponseEntity.ok(calculation);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PenaltyCalculation> getCalculation(@PathVariable Long id) {
        PenaltyCalculation calculation = penaltyCalculationService.getCalculationById(id);
        return ResponseEntity.ok(calculation);
    }
    
    @GetMapping("/contract/{contractId}")
    public ResponseEntity<List<PenaltyCalculation>> getCalculationsForContract(@PathVariable Long contractId) {
        List<PenaltyCalculation> calculations = penaltyCalculationService.getCalculationsForContract(contractId);
        return ResponseEntity.ok(calculations);
    }
}