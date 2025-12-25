package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.entity.Contract;
import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.BreachReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    private final BreachReportRepository reportRepo;
    private final PenaltyCalculationRepository penaltyRepo;
    private final ContractRepository contractRepo;

    public BreachReportServiceImpl(BreachReportRepository reportRepo,
                                   PenaltyCalculationRepository penaltyRepo,
                                   ContractRepository contractRepo) {
        this.reportRepo = reportRepo;
        this.penaltyRepo = penaltyRepo;
        this.contractRepo = contractRepo;
    }

    @Override
    public BreachReport generateReport(Long contractId) {

        Contract contract = contractRepo.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        PenaltyCalculation calc = penaltyRepo
                .findTopByContractIdOrderByCalculatedAtDesc(contractId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No penalty calculation"));

        BreachReport report = BreachReport.builder()
            .contract(contract)
            .daysDelayed(calc.getDaysDelayed())
            .penaltyAmount(calc.getCalculatedPenalty())
            .remarks("Generated from latest penalty calculation")
            .generatedAt(LocalDateTime.now())
            .build();

    }

    @Override
    public BreachReport getReportById(Long id) {
        return reportRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found"));
    }

    @Override
    public List<BreachReport> getReportsForContract(Long contractId) {
        return reportRepo.findByContractId(contractId);
    }

    @Override
    public List<BreachReport> getAllReports() {
        return reportRepo.findAll();
    }
}
