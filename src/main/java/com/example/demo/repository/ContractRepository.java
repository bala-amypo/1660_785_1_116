package com.example.demo.repository;

import com.example.demo.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    Optional<Contract> findByContractNumber(String number);
}
---------
package com.example.demo.repository;

import com.example.demo.entity.DeliveryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DeliveryRecordRepository extends JpaRepository<DeliveryRecord, Long> {

    Optional<DeliveryRecord> findFirstByContractIdOrderByDeliveryDateDesc(Long contractId);

    List<DeliveryRecord> findByContractIdOrderByDeliveryDateAsc(Long contractId);

    // ✔ HQL with named parameter
    @Query("SELECT d FROM DeliveryRecord d " +
           "WHERE d.contract.id = :contractId " +
           "ORDER BY d.deliveryDate DESC")
    List<DeliveryRecord> findLatestDeliveryHql(
            @Param("contractId") Long contractId
    );
}
---------
package com.example.demo.repository;

import com.example.demo.entity.BreachRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BreachRuleRepository extends JpaRepository<BreachRule, Long> {
    Optional<BreachRule> findFirstByActiveTrueOrderByIsDefaultRuleDesc();
}
----------------
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

    // ✔ HQL with named parameter
    @Query("SELECT p FROM PenaltyCalculation p " +
           "WHERE p.contract.id = :contractId " +
           "ORDER BY p.calculatedAt DESC")
    List<PenaltyCalculation> fetchLatestByContractHql(
            @Param("contractId") Long contractId
    );
}
