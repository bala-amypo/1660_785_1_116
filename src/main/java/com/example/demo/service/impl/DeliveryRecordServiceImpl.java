package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.DeliveryRecordService;
import com.example.demo.exception.ResourceNotFoundException;

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


        Contract contract = contractRepo
                .findById(record.getContract().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contract not found")
                );

        record.setContract(contract);
        record.setCreatedAt(LocalDateTime.now());

        return deliveryRepo.save(record);
    }

    @Override
    public DeliveryRecord getRecordById(Long id) {

        return deliveryRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No delivery record")
                );
    }

    @Override
    public List<DeliveryRecord> getDeliveryRecordsForContract(Long contractId) {

        List<DeliveryRecord> records =
                deliveryRepo.findByContractIdOrderByDeliveryDateAsc(contractId);

        if (records == null || records.isEmpty()) {
            throw new ResourceNotFoundException("No delivery record");
        }

        return records;
    }

    @Override
    public DeliveryRecord getLatestDeliveryRecord(Long contractId) {

        DeliveryRecord record =
                deliveryRepo.findFirstByContractIdOrderByDeliveryDateDesc(contractId);

        if (record == null) {
            throw new ResourceNotFoundException("No delivery record");
        }

        return record;
    }
}
