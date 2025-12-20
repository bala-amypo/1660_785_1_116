package com.example.demo.controller;

import com.example.demo.entity.Contract;
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
@RequestMapping("/api/contracts")
public class ContractController {

    private final ContractService service;

    public ContractController(ContractService service) {
        this.service = service;
    }

    @PostMapping
    public Contract create(@RequestBody Contract contract) {
        return service.createContract(contract);
    }

    @PutMapping("/{id}")
    public Contract update(@PathVariable Long id,
                           @RequestBody Contract contract) {
        return service.updateContract(id, contract);
    }

    @GetMapping("/{id}")
    public Contract getById(@PathVariable Long id) {
        return service.getContractById(id);
    }

    @GetMapping
    public List<Contract> getAll() {
        return service.getAllContracts();
    }

    @PutMapping("/{id}/status")
    public void updateStatus(@PathVariable Long id) {
        service.updateContractStatus(id);
    }
}
