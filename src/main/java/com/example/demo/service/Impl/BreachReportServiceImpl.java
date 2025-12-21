package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.entity.BreachReport;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.service.BreachReportService;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    private BreachReportRepository reportRepo;
    private PenaltyCalculationRepository penaltyRepo;
    private ContractRepository contractRepo;

    public BreachReportServiceImpl(
            BreachReportRepository reportRepo,
            PenaltyCalculationRepository penaltyRepo,
            ContractRepository contractRepo) {
        this.reportRepo = reportRepo;
        this.penaltyRepo = penaltyRepo;
        this.contractRepo = contractRepo;
    }

    @Override
    public BreachReport generateReport(Long contractId) {

        Contract contract =
                contractRepo.findById(contractId).orElse(null);

        PenaltyCalculation calc =
                penaltyRepo.findTopByContractIdOrderByCalculatedAtDesc(contractId);

        if (contract == null || calc == null) {
            return null;
        }

        BreachReport report = new BreachReport();
        report.setContract(contract);
        report.setDaysDelayed(calc.getDaysDelayed());
        report.setPenaltyAmount(calc.getCalculatedPenalty());
        report.setReportStatus("GENERATED");
        report.setGeneratedAt(LocalDateTime.now());

        return reportRepo.save(report);
    }

    @Override
    public BreachReport getReportById(Long id) {
        return reportRepo.findById(id).orElse(null);
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
