package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.InboundOrderDto;
import com.meli.freshWarehouse.exception.InboundOrderNotFoundException;
import com.meli.freshWarehouse.exception.WarehouseNotFoundException;
import com.meli.freshWarehouse.model.InboundOrder;
import com.meli.freshWarehouse.model.Representative;
import com.meli.freshWarehouse.repository.InboundOrderRepo;
import org.springframework.stereotype.Service;

@Service
public class InboundOrderService implements IInboundOrder {

   private final InboundOrderRepo inboundOrderRepo;
   private final RepresentativeService representativeService;

    public InboundOrderService(InboundOrderRepo inboundOrderRepo, RepresentativeService representativeService) {
        this.inboundOrderRepo = inboundOrderRepo;
        this.representativeService = representativeService;
    }


    @Override
    public InboundOrder getInboundOrderById(Long inboundOrderId) {
        return inboundOrderRepo.findById(inboundOrderId).orElseThrow(() -> new InboundOrderNotFoundException("Inbound Order ID not found."));
       }


    @Override
    public InboundOrder update(Long id, InboundOrderDto update) {
        InboundOrder order = this.getInboundOrderById(id);
        Representative representative = representativeService.findById(update.getRepresentativeId());
        boolean exists = inboundOrderRepo.existsById(order.getId());
        if (!exists) throw new InboundOrderNotFoundException("Inbound Order ID not found.");
        order.setOrderDate(update.getOrderDate());
        order.setRepresentative(representative);
        return inboundOrderRepo.save(order);
    }

}