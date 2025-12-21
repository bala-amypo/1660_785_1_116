package com.example.demo.controller;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/delivery-records")
public class DeliveryRecordController {

    private DeliveryRecordService deliveryService;

    public DeliveryRecordController(DeliveryRecordService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public Response<DeliveryRecord> create(
            @RequestBody DeliveryRecord record) {
        return ResponseEntity.ok(
                deliveryService.createDeliveryRecord(record));
    }

    @GetMapping("/{id}")
    public DeliveryRecord getById(@PathVariable Long id) {
        return deliveryService.getRecordById(id);
    }

    @GetMapping("/contract/{contractId}")
    public List<DeliveryRecord> getByContract(
            @PathVariable Long contractId) {
        return deliveryService.getDeliveryRecordsForContract(contractId);
    }

    @GetMapping("/contract/{contractId}/latest")
    public DeliveryRecord getLatest(
            @PathVariable Long contractId) {
        return deliveryService.getLatestDeliveryRecord(contractId);
    }
}
