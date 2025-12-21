package com.example.demo.repository;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.BreachReport;
import java.util.List;

@Repository
public interface BreachReportRepository extends JpaRepository<BreachReport,Long>{
    List<BreachReport> findByContractId(Long contractId);
}