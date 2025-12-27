package com.example.demo.controller;

import com.example.demo.entity.Contract;
import com.example.demo.service.ContractService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    private final ContractService service;

    public ContractController(ContractService service) {
        this.service = service;
    }

    // @PostMapping
    // public Contract create(@RequestBody Contract c) {
    //     return service.createContract(c);
    // }

    @PutMapping("/{id}")
    public Contract update(@PathVariable Long id, @RequestBody Contract c) {
        return service.updateContract(id, c);
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
