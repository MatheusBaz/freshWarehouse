package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.model.Batch;
import com.meli.freshWarehouse.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fresh-products/batch")
public class BatchController {

    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @GetMapping("/get")
    public ResponseEntity<Batch> getBatchWithDueDateLess3Weeks(@RequestParam String de,
                                                               @RequestParam String ate){

        return ResponseEntity.ok();

    }
}
