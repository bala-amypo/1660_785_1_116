// package com.example.demo.service.impl;

// import com.example.demo.entity.*;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.repository.*;
// import com.example.demo.service.ContractService;
// import org.springframework.stereotype.Service;

// import java.math.BigDecimal;
// import java.util.List;
// import java.util.Optional;

// @Service
// public class ContractServiceImpl implements ContractService {

//     ContractRepository contractRepository;
//     DeliveryRecordRepository deliveryRecordRepository;

//     @Override
//     public Contract createContract(Contract c) {
//         if (c.getBaseContractValue().compareTo(BigDecimal.ZERO) <= 0)
//             throw new IllegalArgumentException("Base contract value");

//         return contractRepository.save(c);
//     }

//     @Override
//     public Contract updateContract(Long id, Contract c) {
//         Contract ex = getContractById(id);
//         ex.setTitle(c.getTitle());
//         ex.setCounterpartyName(c.getCounterpartyName());
//         ex.setAgreedDeliveryDate(c.getAgreedDeliveryDate());
//         ex.setBaseContractValue(c.getBaseContractValue());
//         return contractRepository.save(ex);
//     }

//     @Override
//     public Contract getContractById(Long id) {
//         return contractRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));
//     }

//     @Override
//     public List<Contract> getAllContracts() {
//         return contractRepository.findAll();
//     }

//     @Override
//     public void updateContractStatus(Long id) {
//         Contract c = getContractById(id);

//         Optional<DeliveryRecord> dr =
//                 deliveryRecordRepository.findFirstByContractIdOrderByDeliveryDateDesc(id);

//         if (dr.isPresent() &&
//                 dr.get().getDeliveryDate().isAfter(c.getAgreedDeliveryDate())) {
//             c.setStatus("BREACHED");
//         } else {
//             c.setStatus("ACTIVE");
//         }

//         contractRepository.save(c);
//     }
// }


package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.ContractService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {
    
    private ContractRepository contractRepository;
    private DeliveryRecordRepository deliveryRecordRepository;

    @Override
    public Contract createContract(Contract contract) {
        if (contract.getBaseContractValue().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Base contract value must be greater than zero");
        }
        return contractRepository.save(contract);
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found with id: " + id));
    }

    @Override
    public Contract updateContract(Long id, Contract contract) {
        Contract existing = getContractById(id);
        existing.setTitle(contract.getTitle());
        existing.setCounterpartyName(contract.getCounterpartyName());
        existing.setAgreedDeliveryDate(contract.getAgreedDeliveryDate());
        existing.setBaseContractValue(contract.getBaseContractValue());
        return contractRepository.save(existing);
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public void updateContractStatus(Long id) {
        Contract contract = getContractById(id);
        Optional<DeliveryRecord> latestDelivery = deliveryRecordRepository
                .findFirstByContractIdOrderByDeliveryDateDesc(id);
        
        if (latestDelivery.isPresent()) {
            DeliveryRecord delivery = latestDelivery.get();
            if (delivery.getDeliveryDate().isAfter(contract.getAgreedDeliveryDate())) {
                contract.setStatus("BREACHED");
            } else {
                contract.setStatus("COMPLETED");
            }
        } else {
            if (LocalDate.now().isAfter(contract.getAgreedDeliveryDate())) {
                contract.setStatus("BREACHED");
            } else {
                contract.setStatus("ACTIVE");
            }
        }
        contractRepository.save(contract);
    }
}