package com.meli.freshWarehouse.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "current_temperature")
    private Float currentTemperature;

    @Column(name = "minimum_temperature")
    private Float minimumTemperature;

    @Column(name = "initial_quantity")
    private Integer initialQuantity;

    @Column(name = "current_quantity")
    private Integer currentQuantity;

    @Column(name = "manufacturing_date")
    private LocalDate manufacturingDate;

    @Column(name = "manufacturing_time")
    private LocalDateTime manufacturingTime;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "sale_off_status")
    private String saleOffStatus;

    @Column(name = "value_descount_sale_off")
    private Double valueDescountSaleOff;

    @ManyToOne
    @JoinColumn(name = "id_order", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_section", nullable = false)
    private Section section;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;


}
