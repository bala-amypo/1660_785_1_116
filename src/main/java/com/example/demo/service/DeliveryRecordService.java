package com.example.demo.service;

import com.example.demo.entity.DeliveryRecordEntity;
import java.util.List;

public interface DeliveryRecordService {

    DeliveryRecordEntity createDeliveryRecord(DeliveryRecordEntity record);

    DeliveryRecordEntity getRecordById(Long id);

    List<DeliveryRecord> getDeliveryRecordsForContract(Long contractId);

    DeliveryRecord getLatestDeliveryRecord(Long contractId);
}
