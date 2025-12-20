package com.example.demo.service.impl;

import com.example.demo.entity.DeliveryRecordEntity;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.repository.ContractRepository;
import com.example.demo.service.DeliveryRecordService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class DeliveryRecordServiceImpl
        implements DeliveryRecordService {

    private DeliveryRecordRepository deliveryRepo;
    private ContractRepository contractRepo;

    public DeliveryRecordServiceImpl(
            DeliveryRecordRepository deliveryRepo,
            ContractRepository contractRepo) {
        this.deliveryRepo = deliveryRepo;
        this.contractRepo = contractRepo;
    }

    @Override
    public DeliveryRecordEntity createDeliveryRecord(
            DeliveryRecordEntity record) {

        if (record.getDeliveryDate().isAfter(LocalDate.now())) {
            return null;
        }

        record.setCreatedAt(LocalDateTime.now());
        return deliveryRepo.save(record);
    }

    @Override
    public DeliveryRecordEntity getRecordById(Long id) {
        return deliveryRepo.findById(id).orElse(null);
    }

    @Override
    public List<DeliveryRecordEntity>
    getDeliveryRecordsForContract(Long contractId) {
        return deliveryRepo
                .findByContractIdOrderByDeliveryDateAsc(contractId);
    }

    @Override
    public DeliveryRecordEntity getLatestDeliveryRecord(Long contractId) {
        return deliveryRepo
                .findFirstByContractIdOrderByDeliveryDateDesc(contractId);
    }
}
