package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.DeliveryRecordService;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private DeliveryRecordRepository deliveryRepo;
    private ContractRepository contractRepo;

    public DeliveryRecordServiceImpl(
            DeliveryRecordRepository deliveryRepo,
            ContractRepository contractRepo) {
        this.deliveryRepo = deliveryRepo;
        this.contractRepo = contractRepo;
    }

    @Override
    public DeliveryRecord createDeliveryRecord(DeliveryRecord record) {

        Contract contract =
                contractRepo.findById(record.getContract().getId())
                        .orElseThrow(() -> new RuntimeException("Contract not found"));

        record.setContract(contract);
        record.setCreatedAt(LocalDateTime.now());
        return deliveryRepo.save(record);
    }

    @Override
    public DeliveryRecord getRecordById(Long id) {
        return deliveryRepo.findById(id).orElse(null);
    }

    @Override
    public List<DeliveryRecord> getDeliveryRecordsForContract(Long contractId) {
        return deliveryRepo.findByContractIdOrderByDeliveryDateAsc(contractId);
    }

    @Override
    public DeliveryRecord getLatestDeliveryRecord(Long contractId) {
        return deliveryRepo.findFirstByContractIdOrderByDeliveryDateDesc(contractId);
    }
}
