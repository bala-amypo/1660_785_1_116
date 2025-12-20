package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.exception.ResourceNotFoundException;
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
    public Contract createContract(Contract contract) {
        contract.setStatus("ACTIVE");
        contract.setCreatedAt(LocalDateTime.now());
        return contractRepo.save(contract);
    }

    @Override
    public Contract updateContract(Long id, Contract contract) {
        Contract c = getContractById(id);

        c.setTitle(contract.getTitle());
        c.setCounterpartyName(contract.getCounterpartyName());
        c.setAgreedDeliveryDate(contract.getAgreedDeliveryDate());
        c.setBaseContractValue(contract.getBaseContractValue());
        c.setUpdatedAt(LocalDateTime.now());

        return contractRepo.save(c);
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contract not found"));
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepo.findAll();
    }

    @Override
    public void updateContractStatus(Long contractId) {
        Contract con = getContractById(contractId);
        con.setUpdatedAt(LocalDateTime.now());
        contractRepo.save(con);
    }
}
