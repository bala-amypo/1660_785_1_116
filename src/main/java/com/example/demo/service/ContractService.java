package com.example.demo.service;

import com.example.demo.entity.ContractEntity;
import java.util.List;

public interface ContractService {

    ContractEntity createContract(ContractEntity contract);

    ContractEntity updateContract(Long id, ContractEntity contract);

    ContractEntity getContractById(Long id);

    List<Contract> getAllContracts();
}
