package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ContractServiceImpl {

    ContractRepository contractRepository;
    DeliveryRecordRepository deliveryRecordRepository;

    public Contract createContract(Contract contract) {
        if (contract.getBaseContractValue() == null ||
                contract.getBaseContractValue().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Base contract value");
        }
        return contractRepository.save(contract);
    }

    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public Contract updateContract(Long id, Contract updated) {
        Contract existing = getContractById(id);

        existing.setTitle(updated.getTitle());
        existing.setCounterpartyName(updated.getCounterpartyName());
        existing.setAgreedDeliveryDate(updated.getAgreedDeliveryDate());
        existing.setBaseContractValue(updated.getBaseContractValue());

        return contractRepository.save(existing);
    }

    public void updateContractStatus(Long id) {
        Contract contract = getContractById(id);

        deliveryRecordRepository
                .findFirstByContractIdOrderByDeliveryDateDesc(id)
                .ifPresent(record -> {
                    if (record.getDeliveryDate()
                            .isAfter(contract.getAgreedDeliveryDate())) {
                        contract.setStatus("BREACHED");
                    } else {
                        contract.setStatus("ACTIVE");
                    }
                });

        contractRepository.save(contract);
    }
}
