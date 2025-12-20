package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.PenaltyCalculationEntity;
import java.util.List;

@Repository
public interface PenaltyCalculationRepository extends JpaRepository<PenaltyCalculationEntity,Long>{
    List<PenaltyCalculationEntity> findByContractId(Long contractId); 
    PenaltyCalculationEntity findTopByContractIdOrderByCalculatedAtDesc(Long contractId);  
}





