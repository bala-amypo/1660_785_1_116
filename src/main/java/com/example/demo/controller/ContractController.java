// package com.example.demo.controller;

// import com.example.demo.entity.Contract;
// import com.example.demo.service.ContractService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/contracts")
// public class ContractController {

//     private final ContractService service;

//     public ContractController(ContractService service) {
//         this.service = service;
//     }

//     @PostMapping
//     public Contract create(@RequestBody Contract c) {
//         return service.createContract(c);
//     }

//     @PutMapping("/{id}")
//     public Contract update(@PathVariable Long id, @RequestBody Contract c) {
//         return service.updateContract(id, c);
//     }

//     @GetMapping("/{id}")
//     public Contract getById(@PathVariable Long id) {
//         return service.getContractById(id);
//     }

//     @GetMapping
//     public List<Contract> getAll() {
//         return service.getAllContracts();
//     }

//     @PutMapping("/{id}/status")
//     public void updateStatus(@PathVariable Long id) {
//         service.updateContractStatus(id);
//     }
// }

package com.example.demo.controller;

import com.example.demo.entity.Contract;
import com.example.demo.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {
    
    @Autowired
    private ContractService contractService;
    
    @PostMapping
    public ResponseEntity<Contract> createContract(@RequestBody Contract contract) {
        Contract created = contractService.createContract(contract);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Contract> getContract(@PathVariable Long id) {
        Contract contract = contractService.getContractById(id);
        return ResponseEntity.ok(contract);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Contract> updateContract(@PathVariable Long id, @RequestBody Contract contract) {
        Contract updated = contractService.updateContract(id, contract);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping
    public ResponseEntity<List<Contract>> getAllContracts() {
        List<Contract> contracts = contractService.getAllContracts();
        return ResponseEntity.ok(contracts);
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateContractStatus(@PathVariable Long id) {
        contractService.updateContractStatus(id);
        return ResponseEntity.ok().build();
    }
}