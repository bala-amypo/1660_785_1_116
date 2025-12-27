package com.example.demo.controller;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-records")
public class DeliveryRecordController {

    private final DeliveryRecordService service;

    public DeliveryRecordController(DeliveryRecordService service) {
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

    @GetMapping("/contract/{id}")
    public List<DeliveryRecord> byContract(@PathVariable Long id) {
        return service.getDeliveryRecordsForContract(id);
    }

    @GetMapping("/contract/{id}/latest")
    public DeliveryRecord latest(@PathVariable Long id) {
        return service.getLatestDeliveryRecord(id);
    }
}


