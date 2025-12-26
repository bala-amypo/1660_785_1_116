package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    Optional<Contract> findByContractNumber(String no);
}
----------
public interface DeliveryRecordRepository extends JpaRepository<DeliveryRecord, Long> {
    Optional<DeliveryRecord> findFirstByContractIdOrderByDeliveryDateDesc(Long id);
    List<DeliveryRecord> findByContractIdOrderByDeliveryDateAsc(Long id);
}
------------
public interface BreachRuleRepository extends JpaRepository<BreachRule, Long> {
    Optional<BreachRule> findFirstByActiveTrueOrderByIsDefaultRuleDesc();
}
-------------
public interface PenaltyCalculationRepository extends JpaRepository<PenaltyCalculation, Long> {
    List<PenaltyCalculation> findByContractId(Long id);
    Optional<PenaltyCalculation> findTopByContractIdOrderByCalculatedAtDesc(Long id);
}
------------------
public interface BreachReportRepository extends JpaRepository<BreachReport, Long> {
    List<BreachReport> findByContractId(Long id);
}
------------------------
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
