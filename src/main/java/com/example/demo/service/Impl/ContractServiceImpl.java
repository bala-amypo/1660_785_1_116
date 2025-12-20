package com.example.demo.service.impl;

import com.example.demo.entity.ContractEntity;
import com.example.demo.repository.ContractRepository;
import com.example.demo.service.ContractService;

import java.time.LocalDateTime;
import java.util.List;

public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepo;

    public ContractServiceImpl(ContractRepository contractRepo) {
        this.contractRepo = contractRepo;
    }

    @Override
    public ContractEntity createContract(ContractEntity contract) {

        contract.setStatus("ACTIVE");
        contract.setCreatedAt(LocalDateTime.now());

        return contractRepo.save(contract);
    }

    @Override
    public ContractEntity updateContract(Long id, ContractEntity contract) {

        ContractEntity existing =
                contractRepo.findById(id).orElse(null);

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
    public ContractEntity getContractById(Long id) {
        return contractRepo.findById(id).orElse(null);
    }

    @Override
    public List<ContractEntity> getAllContracts() {
        return contractRepo.findAll();
    }

    @Override
    public void updateContractStatus(Long id) {
    ContractEntity contract = contractRepo.findById(id).orElse(null);
        if (contract != null) {
        contract.setUpdatedAt(LocalDateTime.now());
        contractRepo.save(contract);
    }
}

}
