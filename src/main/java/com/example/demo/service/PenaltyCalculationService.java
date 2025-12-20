package com.example.demo.service;

import com.example.demo.entity.PenaltyCalculationEntity;
import java.util.List;

public interface PenaltyCalculationService {

    PenaltyCalculationEntity calculatePenalty(Long contractId);

    PenaltyCalculationEntity getCalculationById(Long id);

    List<PenaltyCalculationEntity> getCalculationsForContract(Long contractId);
}
