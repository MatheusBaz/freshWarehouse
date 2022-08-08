package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.RepresentativeDTO;
import com.meli.freshWarehouse.exception.NotFoundException;
import com.meli.freshWarehouse.model.Representative;
import com.meli.freshWarehouse.model.Warehouse;
import com.meli.freshWarehouse.repository.RepresentativeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepresentativeService implements IRepresentativeService {

    private final RepresentativeRepo representativeRepository;
    private final IWarehouseService warehouseService;

    public RepresentativeService(RepresentativeRepo representativeRepository, IWarehouseService warehouseService) {
        this.representativeRepository = representativeRepository;
        this.warehouseService = warehouseService;
    }

    @Override
    public Representative save(RepresentativeDTO representativeDto) {
        Warehouse warehouse = warehouseService.getWarehouseById(representativeDto.getWarehouseId());

        return representativeRepository.save(Representative.builder()
                .name(representativeDto.getName())
                .warehouse(warehouse)
                .build());
    }

    @Override
    public List<Representative> findAll() {
        return representativeRepository.findAll();
    }

    @Override
    public Representative update(long id, RepresentativeDTO representativeDto) {
        Representative representative = this.findById(id);
        Warehouse warehouse = warehouseService.getWarehouseById(representativeDto.getWarehouseId());


        representative.setName(representativeDto.getName());
        representative.setWarehouse(warehouse);
        return representativeRepository.save(representative);
    }

    @Override
    public void delete(Long id) {
        representativeRepository.delete(this.findById(id));
    }

    @Override
    public Representative findById(long id) {
        return representativeRepository.findById(id).orElseThrow(() -> new NotFoundException("No representative was found with this id"));
    }

    @Override
    public boolean existsById(long id) {
        return representativeRepository.existsById(id);
    }
}
