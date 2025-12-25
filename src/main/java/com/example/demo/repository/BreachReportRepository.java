package com.example.demo.repository;

import com.example.demo.entity.BreachReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BreachReportRepository extends JpaRepository<BreachReport, Long> {

    List<BreachReport> findByContractId(Long contractId);
    @Query("SELECT b FROM BreachReport b WHERE b.contract.id = :contractId")
    List<BreachReport> fetchReportsByContractHql(@Param("contractId") Long contractId);
}
