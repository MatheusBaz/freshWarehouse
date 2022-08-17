package com.meli.freshWarehouse.util;

import com.meli.freshWarehouse.dto.BatchSaleOffDto;
import com.meli.freshWarehouse.model.Batch;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GenerateBatchSaleOff {

    public static List<BatchSaleOffDto> validListBatchSaleOffDto(){
        List<BatchSaleOffDto> batchSaleOffDtoList = new ArrayList<>();
        batchSaleOffDtoList.add(
                BatchSaleOffDto.builder()
                        .id(1L)
                        .status("Being performed")
                        .valueDescount(30D)
                        .saleTotalWithDescount(100D)
                        .build()
        );
        batchSaleOffDtoList.add(
                BatchSaleOffDto.builder()
                        .id(2L)
                        .status("Finished")
                        .valueDescount(51D)
                        .saleTotalWithDescount(121D)
                        .build()
        );
        return batchSaleOffDtoList;
    }

    public static BatchSaleOffDto validBatchSaleOffDto(){
         return BatchSaleOffDto.builder()
                .id(1L)
                .status("Being performed")
                .valueDescount(30D)
                .saleTotalWithDescount(100D)
                .build();
    }

    public static Batch validBatch() {
        return Batch.builder()
                .id(1L)
                .currentTemperature(24F)
                .minimumTemperature(25F)
                .initialQuantity(2)
                .currentQuantity(2)
                .manufacturingDate(LocalDate.parse("2021-06-03"))
                .manufacturingTime(LocalDateTime.parse("2021-06-03T14:24:54"))
                .dueDate(LocalDate.parse("2021-07-24"))
                .section(GenerateSection.validSection1())
                .order(GenerateOrder.validOrder1())
                .product(GenerateProduct.validProduct1())
                .build();
    }

    public static List<Batch> validBatchListForSale() {
        List<Batch> batchSaleOffDtoList = new ArrayList<>();
        batchSaleOffDtoList.add(
                Batch.builder()
                        .id(1L)
                        .currentTemperature(24F)
                        .minimumTemperature(25F)
                        .initialQuantity(2)
                        .currentQuantity(2)
                        .saleOffStatus("Being performed")
                        .valueDescountSaleOff(30D)
                        .manufacturingDate(LocalDate.parse("2021-06-03"))
                        .manufacturingTime(LocalDateTime.parse("2021-06-03T14:24:54"))
                        .dueDate(LocalDate.parse("2021-07-24"))
                        .section(GenerateSection.validSection1())
                        .order(GenerateOrder.validOrder1())
                        .product(GenerateProduct.validProduct1())
                        .build()
        );
        batchSaleOffDtoList.add(
                Batch.builder()
                        .id(2L)
                        .currentTemperature(24F)
                        .minimumTemperature(25F)
                        .initialQuantity(2)
                        .currentQuantity(2)
                        .saleOffStatus("Being performed")
                        .valueDescountSaleOff(30D)
                        .manufacturingDate(LocalDate.parse("2022-06-03"))
                        .manufacturingTime(LocalDateTime.parse("2021-06-03T14:24:54"))
                        .dueDate(LocalDate.parse("2021-07-24"))
                        .section(GenerateSection.validSection1())
                        .order(GenerateOrder.validOrder1())
                        .product(GenerateProduct.validProduct1())
                        .build()
        );
        return batchSaleOffDtoList;
    }


    public static Batch validBatchWithDiscount() {
        return Batch.builder()
                .id(1L)
                .currentTemperature(24F)
                .minimumTemperature(25F)
                .initialQuantity(2)
                .currentQuantity(2)
                .valueDescountSaleOff(30D)
                .manufacturingDate(LocalDate.parse("2021-06-03"))
                .manufacturingTime(LocalDateTime.parse("2021-06-03T14:24:54"))
                .dueDate(LocalDate.parse("2021-07-24"))
                .section(GenerateSection.validSection1())
                .order(GenerateOrder.validOrder1())
                .product(GenerateProduct.validProduct1())
                .build();
    }
}
