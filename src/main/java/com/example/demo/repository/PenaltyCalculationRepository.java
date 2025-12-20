package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.PenaltyCalculationEntity;

@Repository
public interface PenaltyCalculationRepository extends JpaRepository<PenaltyCalculationEntity,Long>{
    
}





