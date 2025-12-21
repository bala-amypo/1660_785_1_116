package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.repository.ContractRepository;
import com.example.demo.service.ContractService;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepo;

    public ContractServiceImpl(ContractRepository contractRepo) {
        this.contractRepo = contractRepo;
    }

    @Override
    public Contract createContract(Contract contract) {
        contract.setStatus("ACTIVE");
        contract.setCreatedAt(LocalDateTime.now());
        return contractRepo.save(contract);
    }

    @Override
    public Contract updateContract(Long id, Contract contract) {
        Contract existing = contractRepo.findById(id).orElse(null);
        if (existing != null) {
            existing.setTitle(contract.getTitle());
            existing.setCounterpartyName(contract.getCounterpartyName());
            existing.setAgreedDeliveryDate(contract.getAgreedDeliveryDate());
            existing.setBaseContractValue(contract.getBaseContractValue());
            existing.setUpdatedAt(LocalDateTime.now());
            return contractRepo.save(existing);
        }
        return null;
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepo.findById(id).orElse(null);
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepo.findAll();
    }

    @Override
    public void updateContractStatus(Long id) {
        Contract contract = contractRepo.findById(id).orElse(null);
        if (contract != null) {
            contract.setUpdatedAt(LocalDateTime.now());
            contractRepo.save(contract);
        }
    }
}
