package com.example.demo.repository;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.BreachReportEntity;
import java.util.List;

@Repository
public interface BreachReportRepository extends JpaRepository<BreachReportEntity,Long>{
    List<BreachReportEntity> findByContractId(Long contractId);
}