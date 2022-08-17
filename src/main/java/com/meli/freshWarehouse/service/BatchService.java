package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.BatchSaleOffDto;
import com.meli.freshWarehouse.dto.DueDateResponseDto;
import com.meli.freshWarehouse.exception.*;
import com.meli.freshWarehouse.model.Batch;
import com.meli.freshWarehouse.model.Section;
import com.meli.freshWarehouse.repository.BatchRepo;
import com.meli.freshWarehouse.repository.DueDateRepo;
import com.meli.freshWarehouse.repository.ISectionRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BatchService implements IBatchService {

    private final BatchRepo batchRepo;
    private final ISectionRepo iSectionRepo;
    private final DueDateRepo dueDateRepo;

    public BatchService(BatchRepo batchRepo, ISectionRepo iSectionRepo, DueDateRepo dueDateRepo) {
        this.batchRepo = batchRepo;
        this.iSectionRepo = iSectionRepo;
        this.dueDateRepo = dueDateRepo;
    }

    @Override
    public List<Batch> saveAll(List<Batch> batchList) {
        return batchRepo.saveAll(batchList);
    }

    public BatchSaleOffDto updateSaleOff(Long id, Double valueDescount){
        Batch batch = batchRepo.findById(id).orElseThrow(() ->
                new NotFoundException("Can't find batch with the informed id"));

        if (valueDescount == null && batch.getValueDescountSaleOff() == null) {
            throw new InvalidBatchSaleOffParam("You need to inform the value of descount to start a sale off!");
        } else if(valueDescount != null && batch.getValueDescountSaleOff() == null) {
            batch.setSaleOffStatus("Being performed");
            batch.setValueDescountSaleOff(valueDescount);
        } else if(valueDescount == null && batch.getValueDescountSaleOff() != null ) {
            batch.setSaleOffStatus("Finished");
        } else if(valueDescount != null && batch.getValueDescountSaleOff() != null) {
            batch.setValueDescountSaleOff(valueDescount);
        } else {
            throw new InvalidBatchSaleOffParam("Params sales off invalids for this batch!");
        }

        batchRepo.save(batch);
        return BatchSaleOffDto.builder()
                .id(batch.getId())
                .saleTotalWithDescount(0D)
                .valueDescount(batch.getValueDescountSaleOff())
                .status(batch.getSaleOffStatus())
                .build();
    }

    public List<BatchSaleOffDto> getbatchesWithSaleOff(String dateFrom, String dateUntil){
        LocalDate dateFromDate = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dateUntilDate = LocalDate.parse(dateUntil, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<Batch> batch = batchRepo.findBatchByDueDateBetween(dateFromDate,dateUntilDate)
                .stream().filter(b -> b.getSaleOffStatus() != null).collect(Collectors.toList());

        List<BatchSaleOffDto> batchSaleOffDtoList = new ArrayList<>();

        batch.forEach(b -> {
            Double saleTotal = ( b.getInitialQuantity() - b.getCurrentQuantity() ) *
                               ( b.getProduct().getPrice() - ((b.getProduct().getPrice()) * (b.getValueDescountSaleOff() / 100)) );

            batchSaleOffDtoList.add(
                    BatchSaleOffDto.builder()
                            .id(b.getId())
                            .status(b.getSaleOffStatus())
                            .valueDescount(b.getValueDescountSaleOff())
                            .saleTotalWithDescount(saleTotal)
                            .build()
            );
        });

        return batchSaleOffDtoList;
    }

    @Override
    public List<DueDateResponseDto> getBySectionAndDueDate(Long sectionId, Integer amountOfDays) {
        if (amountOfDays == null) {
            Section sectionById = iSectionRepo.findById(sectionId).orElseThrow(() -> new SectionIdNotFoundException("Couldn't find any Section by this ID"));
            return dueDateRepo.getBySection(sectionById);
        }
        else {
            return dueDateRepo.getBySectionAndDueDate(iSectionRepo.findById(sectionId).orElseThrow(() -> new SectionIdNotFoundException("Couldn't find any Section by this ID")),
                    LocalDate.now(), LocalDate.now().plusDays(amountOfDays));
        }
    }

    @Override
    public List<DueDateResponseDto> getBySectionAndDueDate(String sectionName, Integer amountOfDays) {
        List<DueDateResponseDto> batchList = new ArrayList<>();
        Set<Section> sectionList = new HashSet<>();
        switch (sectionName) {
            case "FS":
                sectionList.addAll(iSectionRepo.findByName("Fresh"));
                sectionList.forEach(section -> batchList.addAll(getBySectionAndDueDate(section.getId(), amountOfDays)));
                if (sectionList.isEmpty()) {
                    throw new EmptySectionListException("Couldn't find any products in this Section");
                }

                return batchList;
            case "RF":
                sectionList.addAll(iSectionRepo.findByName("Refrigerated"));
                sectionList.forEach(section -> batchList.addAll(getBySectionAndDueDate(section.getId(), amountOfDays)));
                if (sectionList.isEmpty()) {
                    throw new EmptySectionListException("Couldn't find any products in this Section");
                }

                return batchList;
            case "FF":
                sectionList.addAll(iSectionRepo.findByName("Frozen"));
                sectionList.forEach(section -> batchList.addAll(getBySectionAndDueDate(section.getId(), amountOfDays)));
                if (sectionList.isEmpty()) {
                    throw new EmptySectionListException("Couldn't find any products in this Section");
                }

                return batchList;
            default:
                throw new InvalidSectionNameException("Please, enter one of the options: FS, RF or FF");
        }
    }
}
