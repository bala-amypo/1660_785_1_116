package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.PenaltyCalculation;
import java.util.List;

@Repository
public interface PenaltyCalculationRepository extends JpaRepository<PenaltyCalculation,Long>{
    List<PenaltyCalculation> findByContractId(Long contractId); 
    PenaltyCalculation findTopByContractIdOrderByCalculatedAtDesc(Long contractId);  
}





