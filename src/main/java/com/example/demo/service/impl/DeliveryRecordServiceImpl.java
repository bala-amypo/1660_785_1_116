package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private final DeliveryRecordRepository deliveryRepo;
    private final ContractRepository contractRepo;

    public DeliveryRecordServiceImpl(DeliveryRecordRepository deliveryRepo,
                                     ContractRepository contractRepo) {
        this.deliveryRepo = deliveryRepo;
        this.contractRepo = contractRepo;
    }

    @Override
    public DeliveryRecord createDeliveryRecord(DeliveryRecord record) {

        if (record.getDeliveryDate().isAfter(LocalDate.now())) {
            throw new BadRequestException("Delivery date cannot be in the future");
        }

        Long contractId = record.getContract().getId();
        Contract contract = contractRepo.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        record.setContract(contract);
        return deliveryRepo.save(record);
    }

    @Override
    public DeliveryRecord getRecordById(Long id) {
        return deliveryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery record not found"));
    }

    @Override
    public List<DeliveryRecord> getDeliveryRecordsForContract(Long contractId) {
        return deliveryRepo.findByContractIdOrderByDeliveryDateAsc(contractId);
    }

    @Override
    public DeliveryRecord getLatestDeliveryRecord(Long contractId) {
        return deliveryRepo.findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No delivery records found"));
    }
}
