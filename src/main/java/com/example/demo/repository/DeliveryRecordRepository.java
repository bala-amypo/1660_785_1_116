package com.example.demo.repository;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.DeliveryRecord;
import java.util.List;

@Repository
public interface DeliveryRecordRepository extends JpaRepository<DeliveryRecord,Long>{
    List<DeliveryRecord>
    findByContractIdOrderByDeliveryDateAsc(Long contractId);  

    DeliveryRecord
    findFirstByContractIdOrderByDeliveryDateDesc(Long contractId);
}
