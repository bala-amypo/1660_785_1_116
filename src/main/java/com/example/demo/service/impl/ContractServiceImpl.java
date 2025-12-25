package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.ContractService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepo;
    private final DeliveryRecordRepository deliveryRepo;

    public ContractServiceImpl(ContractRepository contractRepo,
                               DeliveryRecordRepository deliveryRepo) {
        this.contractRepo = contractRepo;
        this.deliveryRepo = deliveryRepo;
    }

    @Override
    public Contract createContract(Contract contract) {
        if (contract.getBaseContractValue() == null ||
                contract.getBaseContractValue().doubleValue() <= 0) {
            throw new BadRequestException("Base contract value must be greater than zero");
        }
        contract.setStatus("ACTIVE");
        return contractRepo.save(contract);
    }

    @Override
    public Contract updateContract(Long id, Contract updated) {
        Contract existing = contractRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        existing.setTitle(updated.getTitle());
        existing.setCounterpartyName(updated.getCounterpartyName());
        existing.setAgreedDeliveryDate(updated.getAgreedDeliveryDate());
        existing.setBaseContractValue(updated.getBaseContractValue());

        return contractRepo.save(existing);
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepo.findAll();
    }

    @Override
    public void updateContractStatus(Long contractId) {
        Contract contract = getContractById(contractId);

        deliveryRepo.findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .ifPresent(record -> {
                    if (record.getDeliveryDate()
                            .isAfter(contract.getAgreedDeliveryDate())) {
                        contract.setStatus("BREACHED");
                    }
                });

        contractRepo.save(contract);
    }
}
