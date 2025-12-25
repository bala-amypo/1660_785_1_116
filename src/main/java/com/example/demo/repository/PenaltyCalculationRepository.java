package com.example.demo.repository;

import com.example.demo.entity.PenaltyCalculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PenaltyCalculationRepository
        extends JpaRepository<PenaltyCalculation, Long> {

    Optional<PenaltyCalculation> findTopByContractIdOrderByCalculatedAtDesc(Long contractId);

    List<PenaltyCalculation> findByContractId(Long contractId);

    @Query("SELECT p FROM PenaltyCalculation p " +
           "WHERE p.contract.id = :contractId " +
           "ORDER BY p.calculatedAt DESC")
    List<PenaltyCalculation> fetchLatestByContractHql(@Param("contractId") Long contractId);
}
