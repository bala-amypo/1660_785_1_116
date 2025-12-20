package com.example.demo.service.impl;

import com.example.demo.entity.ContractEntity;
import com.example.demo.entity.PenaltyCalculationEntity;
import com.example.demo.entity.BreachReportEntity;

import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.repository.BreachReportRepository;

import com.example.demo.service.BreachReportService;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BreachReportServiceImpl
        implements BreachReportService {

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
    public BreachReportEntity generateReport(Long contractId) {

        ContractEntity contract =
                contractRepo.findById(contractId).orElse(null);

        PenaltyCalculationEntity calc =
                penaltyRepo
                        .findTopByContractIdOrderByCalculatedAtDesc(contractId);

        if (contract == null || calc == null) {
            return null;
        }

        BreachReportEntity report =
                new BreachReportEntity();

        report.setContract(contract);
        report.setDaysDelayed(calc.getDaysDelayed());
        report.setPenaltyAmount(calc.getCalculatedPenalty());
        report.setReportStatus("GENERATED");
        report.setGeneratedAt(LocalDateTime.now());

        return reportRepo.save(report);
    }

    @Override
    public BreachReportEntity getReportById(Long id) {
        return reportRepo.findById(id).orElse(null);
    }

    @Override
    public List<BreachReportEntity>
    getReportsForContract(Long contractId) {
        return reportRepo.findByContractId(contractId);
    }

    @Override
    public List<BreachReportEntity> getAllReports() {
        return reportRepo.findAll();
}

}
