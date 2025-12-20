package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecordEntity;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.ContractService;

import java.time.LocalDateTime;
import java.util.List;

public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepo;
    private DeliveryRecordRepository deliveryRepo;

    public ContractServiceImpl(ContractRepository contractRepo,
                               DeliveryRecordRepository deliveryRepo) {
        this.contractRepo = contractRepo;
        this.deliveryRepo = deliveryRepo;
    }

    @Override
    public Contract createContract(Contract contract) {
        contract.setStatus("ACTIVE");
        contract.setCreatedAt(LocalDateTime.now());
        return contractRepo.save(contract);
    }

    }