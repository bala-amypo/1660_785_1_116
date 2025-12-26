package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachReportServiceImpl {

    BreachReportRepository breachReportRepository;
    PenaltyCalculationRepository penaltyCalculationRepository;
    ContractRepository contractRepository;

    public BreachReport generateReport(Long contractId) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("not found"));

        PenaltyCalculation calculation =
                penaltyCalculationRepository
                        .findTopByContractIdOrderByCalculatedAtDesc(contractId)
                        .orElseThrow(() ->
                                new RuntimeException("No penalty calculation"));

        return breachReportRepository.save(
                BreachReport.builder()
                        .contract(contract)
                        .daysDelayed(calculation.getDaysDelayed())
                        .penaltyAmount(calculation.getCalculatedPenalty())
                        .build()
        );
    }

    public List<BreachReport> getReportsForContract(Long contractId) {
        return breachReportRepository.findByContractId(contractId);
    }

    public List<BreachReport> getAllReports() {
        return breachReportRepository.findAll();
    }
}
