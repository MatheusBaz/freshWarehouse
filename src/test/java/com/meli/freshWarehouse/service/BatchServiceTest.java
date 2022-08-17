package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.BatchSaleOffDto;
import com.meli.freshWarehouse.exception.InvalidBatchSaleOffParam;
import com.meli.freshWarehouse.exception.SectionIdNotFoundException;
import com.meli.freshWarehouse.model.Batch;
import com.meli.freshWarehouse.repository.BatchRepo;
import com.meli.freshWarehouse.util.GenerateBachStock;
import com.meli.freshWarehouse.util.GenerateBatchSaleOff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LOCAL_DATE;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BatchServiceTest {

    @InjectMocks
    private BatchService batchService;

    @Spy
    private BatchRepo batchRepo;

   @BeforeEach
   public void setUp(){
       MockitoAnnotations.openMocks(this);
   }

   @Test
    public void saveAll(){

       List<Batch> batchList = List.of(GenerateBachStock.newBatch2());

       BDDMockito.when(batchRepo.saveAll(batchList))
               .thenReturn(batchList);

       batchService.saveAll(batchList);
       assertThat(batchList).isNotNull();
       verify(batchRepo, Mockito.times(1)).saveAll(batchList);
   }

   @Test
    public void updateSaleOff_whenIsStating() {
       BDDMockito.when(batchRepo.findById(ArgumentMatchers.anyLong()))
               .thenReturn(Optional.of(GenerateBatchSaleOff.validBatch()));

       Batch batch = GenerateBatchSaleOff.validBatch();

       BatchSaleOffDto batchSaleOffDto =  batchService.updateSaleOff(batch.getId(), 30D);

       assertThat(batchSaleOffDto.getStatus()).isEqualTo("Being performed");
       assertThat(batchSaleOffDto.getValueDescount()).isEqualTo(30D);
       assertThat(batchSaleOffDto.getId()).isEqualTo(batch.getId());
       verify(batchRepo, atLeastOnce()).findById(ArgumentMatchers.anyLong());
   }

    @Test
    public void updateSaleOff_whenIsFinishing() {
        BDDMockito.when(batchRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(GenerateBatchSaleOff.validBatchWithDiscount()));

        Batch batch = GenerateBatchSaleOff.validBatchWithDiscount();

        BatchSaleOffDto batchSaleOffDto =  batchService.updateSaleOff(batch.getId(), null);

        assertThat(batchSaleOffDto.getStatus()).isEqualTo("Finished");
        assertThat(batchSaleOffDto.getValueDescount()).isEqualTo(batch.getValueDescountSaleOff());
        assertThat(batchSaleOffDto.getId()).isEqualTo(batch.getId());
        verify(batchRepo, atLeastOnce()).findById(ArgumentMatchers.anyLong());
    }

    @Test
    public void updateSaleOff_whenIsInvalid() {
        BDDMockito.when(batchRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(GenerateBatchSaleOff.validBatch()));

        Batch batch = GenerateBatchSaleOff.validBatch();

        Exception exception = assertThrows(InvalidBatchSaleOffParam.class,
                () -> batchService.updateSaleOff(batch.getId(), null));

        assertThat(exception.getMessage()).isEqualTo("You need to inform the value of descount to start a sale off!");
        verify(batchRepo, atLeastOnce()).findById(ArgumentMatchers.anyLong());
    }

    @Test
    public void updateSaleOff_whenIsValid() {
        BDDMockito.when(batchRepo.findBatchByDueDateBetween(ArgumentMatchers.any(LocalDate.class),
                                                            ArgumentMatchers.any(LocalDate.class)))
                .thenReturn(GenerateBatchSaleOff.validBatchListForSale());

        List<BatchSaleOffDto> batchSaleOffDtoList =
                batchService.getbatchesWithSaleOff("2022-09-21", "2022-10-22");

        assertThat(batchSaleOffDtoList.get(0).getId()).isPositive();
        assertThat(batchSaleOffDtoList.get(0).getStatus()).isEqualTo("Being performed");
        verify(batchRepo, atLeastOnce()).findBatchByDueDateBetween(ArgumentMatchers.any(LocalDate.class),
                                                                    ArgumentMatchers.any(LocalDate.class));

    }

}