package com.example.demo.service;

import com.example.demo.entity.BreachReportEntity;
import java.util.List;

public interface BreachReportService {

    BreachReportEntity generateReport(Long contractId);

    BreachReportEntity getReportById(Long id);

    List<BreachReportEntity> getReportsForContract(Long contractId);

    List<BreachReportEntity> getAllReports();

}
