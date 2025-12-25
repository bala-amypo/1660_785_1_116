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

    @Query("SELECT d FROM DeliveryRecord d " +
           "WHERE d.contract.id = :contractId " +
           "ORDER BY d.deliveryDate DESC")
    List<DeliveryRecord> findLatestDeliveryHql(@Param("contractId") Long contractId);
}
