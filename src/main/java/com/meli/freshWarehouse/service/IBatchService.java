package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.DueDateResponseDto;
import com.meli.freshWarehouse.model.Batch;

import java.util.List;

public interface IBatchService{
    List<Batch> saveAll(List<Batch> batchList);
    List<DueDateResponseDto> getBySectionAndDueDate(Long sectionId, Integer amountOfDays);
    List<DueDateResponseDto> getBySectionAndDueDate(String sectionName, Integer amountOfDays);
}
