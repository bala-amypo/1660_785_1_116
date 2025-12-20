package com.example.demo.controller;

import com.example.demo.entity.ContractEntity;
import com.example.demo.service.ContractService;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/contracts")
public class ContractController {

    private ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping
    public ContractEntity createContract(
            @RequestBody ContractEntity contract) {
        return contractService.createContract(contract);
    }

    @PutMapping("/{id}")
    public ContractEntity updateContract(
            @PathVariable Long id,
            @RequestBody ContractEntity contract) {
        return contractService.updateContract(id, contract);
    }

    @GetMapping("/{id}")
    public ContractEntity getContractById(
            @PathVariable Long id) {
        return contractService.getContractById(id);
    }

    @GetMapping
    public List<ContractEntity> getAllContracts() {
        return contractService.getAllContracts();
    }
    public void updateContractStatus(
            @PathVariable Long id) {
        contractService.updateContractStatus(id);
    }

    @PutMapping("/{id}/status")
    public void updateContractStatus(@PathVariable Long id) {
    contractService.updateContractStatus(id);
}

}
