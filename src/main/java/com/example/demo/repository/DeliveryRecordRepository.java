package com.example.demo.repository;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.DeliveryRecordEntity;
import java.util.List;

@Repository
public interface DeliveryRecordRepository extends JpaRepository<DeliveryRecordEntity,Long>{
    List<DeliveryRecordEntity>
    findByContractIdOrderByDeliveryDateAsc(Long contractId);  

    DeliveryRecordEntity
    findFirstByContractIdOrderByDeliveryDateDesc(Long contractId);
}
