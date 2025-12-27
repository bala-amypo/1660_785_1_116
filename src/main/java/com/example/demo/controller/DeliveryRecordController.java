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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-records")
public class DeliveryRecordController {
    
    @Autowired
    private DeliveryRecordService deliveryRecordService;
    
    @PostMapping
    public ResponseEntity<DeliveryRecord> createDeliveryRecord(@RequestBody DeliveryRecord deliveryRecord) {
        DeliveryRecord created = deliveryRecordService.createDeliveryRecord(deliveryRecord);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DeliveryRecord> getDeliveryRecord(@PathVariable Long id) {
        DeliveryRecord record = deliveryRecordService.getRecordById(id);
        return ResponseEntity.ok(record);
    }
    
    @GetMapping("/contract/{contractId}/latest")
    public ResponseEntity<DeliveryRecord> getLatestDeliveryRecord(@PathVariable Long contractId) {
        DeliveryRecord record = deliveryRecordService.getLatestDeliveryRecord(contractId);
        return ResponseEntity.ok(record);
    }
    
    @GetMapping("/contract/{contractId}")
    public ResponseEntity<List<DeliveryRecord>> getDeliveryRecordsForContract(@PathVariable Long contractId) {
        List<DeliveryRecord> records = deliveryRecordService.getDeliveryRecordsForContract(contractId);
        return ResponseEntity.ok(records);
    }
}