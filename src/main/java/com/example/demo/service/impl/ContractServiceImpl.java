package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.ContractService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {

    ContractRepository contractRepository;
    DeliveryRecordRepository deliveryRecordRepository;

    @Override
    public Contract createContract(Contract c) {
        if (c.getBaseContractValue().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Base contract value");

        return contractRepository.save(c);
    }

    @Override
    public Contract updateContract(Long id, Contract c) {
        Contract ex = getContractById(id);
        ex.setTitle(c.getTitle());
        ex.setCounterpartyName(c.getCounterpartyName());
        ex.setAgreedDeliveryDate(c.getAgreedDeliveryDate());
        ex.setBaseContractValue(c.getBaseContractValue());
        return contractRepository.save(ex);
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public void updateContractStatus(Long id) {
        Contract c = getContractById(id);

        Optional<DeliveryRecord> dr =
                deliveryRecordRepository.findFirstByContractIdOrderByDeliveryDateDesc(id);

        if (dr.isPresent() &&
                dr.get().getDeliveryDate().isAfter(c.getAgreedDeliveryDate())) {
            c.setStatus("BREACHED");
        } else {
            c.setStatus("ACTIVE");
        }

        contractRepository.save(c);
    }
}
