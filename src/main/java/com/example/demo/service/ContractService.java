package com.example.demo.service;

import com.example.demo.entity.ContractEntity;
import java.util.List;

public interface ContractService {

    Contract createContract(ContractEntity contract);

    Contract updateContract(Long id, ContractEntity contract);

    Contract getContractById(Long id);

    List<Contract> getAllContracts();
}
