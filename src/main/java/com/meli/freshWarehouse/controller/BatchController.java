package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.dto.BatchSaleOffDto;
import com.meli.freshWarehouse.model.Batch;
import com.meli.freshWarehouse.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/batch")
public class BatchController {

    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<BatchSaleOffDto>> getbatchesWithSaleOff(@RequestParam String dateFrom,
                                                                       @RequestParam String dateUntil){
        return ResponseEntity.ok(batchService.getbatchesWithSaleOff(dateFrom, dateUntil));
    }

    @PutMapping("/{id}/sale-off")
    public ResponseEntity<BatchSaleOffDto> updateStatusSale(@PathVariable Long id,
                                                            @RequestParam(required = false) Double valueDescount){
        return ResponseEntity.ok(batchService.updateSaleOff(id, valueDescount));
    }

}
