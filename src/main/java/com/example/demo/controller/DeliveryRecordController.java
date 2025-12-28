// package com.example.demo.controller;

// import com.example.demo.entity.DeliveryRecord;
// import com.example.demo.service.DeliveryRecordService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/delivery-records")
// public class DeliveryRecordController {

//     private final DeliveryRecordService service;

//     public DeliveryRecordController(DeliveryRecordService service) {
//         this.service = service;
//     }

//     @PostMapping
//     public DeliveryRecord create(@RequestBody DeliveryRecord r) {
//         return service.createDeliveryRecord(r);
//     }

//     @GetMapping("/{id}")
//     public DeliveryRecord get(@PathVariable Long id) {
//         return service.getRecordById(id);
//     }

//     @GetMapping("/contract/{id}")
//     public List<DeliveryRecord> byContract(@PathVariable Long id) {
//         return service.getDeliveryRecordsForContract(id);
//     }

//     @GetMapping("/contract/{id}/latest")
//     public DeliveryRecord latest(@PathVariable Long id) {
//         return service.getLatestDeliveryRecord(id);
//     }
// }



package com.example.demo.controller;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-records")
public class DeliveryRecordController {

    private final DeliveryRecordService deliveryRecordService;

    public DeliveryRecordController(DeliveryRecordService deliveryRecordService) {
        this.deliveryRecordService = deliveryRecordService;
    }

    @PostMapping
    public DeliveryRecord create(@RequestBody DeliveryRecord record) {
        return deliveryRecordService.createDeliveryRecord(record);
    }

    @GetMapping("/{id}")
    public DeliveryRecord getById(@PathVariable Long id) {
        return deliveryRecordService.getRecordById(id);
    }

    @GetMapping("/contract/{contractId}")
    public List<DeliveryRecord> getByContract(@PathVariable Long contractId) {
        return deliveryRecordService.getDeliveryRecordsForContract(contractId);
    }

    @GetMapping("/contract/{contractId}/latest")
    public DeliveryRecord getLatest(@PathVariable Long contractId) {
        return deliveryRecordService.getLatestDeliveryRecord(contractId);
    }
}
