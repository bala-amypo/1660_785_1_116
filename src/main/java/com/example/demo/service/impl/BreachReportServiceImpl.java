package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.BreachReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    BreachReportRepository breachReportRepository;
    PenaltyCalculationRepository penaltyCalculationRepository;
    ContractRepository contractRepository;

    @Override
    public BreachReport generateReport(Long contractId) {

        Contract c = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        PenaltyCalculation pc =
                penaltyCalculationRepository.findTopByContractIdOrderByCalculatedAtDesc(contractId)
                        .orElseThrow(() -> new ResourceNotFoundException("No penalty calculation"));

        return breachReportRepository.save(
                BreachReport.builder()
                        .contract(c)
                        .daysDelayed(pc.getDaysDelayed())
                        .penaltyAmount(pc.getCalculatedPenalty())
                        .build());
    }

    @Override
    public List<BreachReport> getReportsForContract(Long contractId) {
        return breachReportRepository.findByContractId(contractId);
    }

    @Override
    public List<BreachReport> getAllReports() {
        return breachReportRepository.findAll();
    }
}
