package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.dto.BatchSaleOffDto;
import com.meli.freshWarehouse.model.Batch;
import com.meli.freshWarehouse.service.BatchService;
import com.meli.freshWarehouse.util.GenerateBachStock;
import com.meli.freshWarehouse.util.GenerateBatchSaleOff;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BatchControllerTest {

    @InjectMocks
    private BatchController batchController;

    @Mock
    private BatchService batchService;

    @Test
    public void getbatchesWithSaleOff() {
        BDDMockito.when(batchService.getbatchesWithSaleOff(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(GenerateBatchSaleOff.validListBatchSaleOffDto());

        ResponseEntity<List<BatchSaleOffDto>> response =
                batchController.getbatchesWithSaleOff("2022-08-12", "2023-08-17");


        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()).isGreaterThan(0);

        Mockito.verify(batchService, Mockito.atLeastOnce())
                .getbatchesWithSaleOff(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
    }

    @Test
    public void updateStatusSale() {
        BDDMockito.when(batchService.updateSaleOff(ArgumentMatchers.anyLong(), ArgumentMatchers.anyDouble()))
                .thenReturn(GenerateBatchSaleOff.validBatchSaleOffDto());

        ResponseEntity<BatchSaleOffDto> response =
                batchController.updateStatusSale(1L, 30D);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getId()).isEqualTo(1L);
        assertThat(response.getBody().getValueDescount()).isEqualTo(30D);

        Mockito.verify(batchService, Mockito.atLeastOnce())
                .updateSaleOff(ArgumentMatchers.anyLong(), ArgumentMatchers.anyDouble());
    }
}
