package com.example.demo.repository;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.DeliveryRecordEntity;

@Repository
public interface DeliveryRecordRepository extends JpaRepository<DeliveryRecordEntity,Long>{
    
}
