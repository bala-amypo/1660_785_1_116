package com.example.demo.service;

import com.example.demo.entity.DeliveryRecordEntity;
import java.util.List;

public interface DeliveryRecordService {

    DeliveryRecordEntity createDeliveryRecord(DeliveryRecordEntity record);

    DeliveryRecordEntity getRecordById(Long id);

    List<DeliveryRecordEntity> getDeliveryRecordsForContract(Long contractId);

    DeliveryRecordEntity getLatestDeliveryRecord(Long contractId);
}
