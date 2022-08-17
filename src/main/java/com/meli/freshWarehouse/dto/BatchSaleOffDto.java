package com.meli.freshWarehouse.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BatchSaleOffDto {
    private Long id;
    private String status;
    private Double valueDescount;
    private Double saleTotalWithDescount;
}
