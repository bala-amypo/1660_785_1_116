package com.example.demo.controller;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.service.impl.DeliveryRecordServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-records")
public class DeliveryRecordController {

    private final DeliveryRecordServiceImpl service;

    public DeliveryRecordController(DeliveryRecordServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public DeliveryRecord create(@RequestBody DeliveryRecord r) {
        return service.createDeliveryRecord(r);
    }

    @GetMapping("/{id}")
    public DeliveryRecord get(@PathVariable Long id) {
        return service.getRecordById(id);
    }

    @GetMapping("/contract/{contractId}")
    public List<DeliveryRecord> getForContract(@PathVariable Long contractId) {
        return service.getDeliveryRecordsForContract(contractId);
    }

    @GetMapping("/contract/{contractId}/latest")
    public DeliveryRecord latest(@PathVariable Long contractId) {
        return service.getLatestDeliveryRecord(contractId);
    }
}
