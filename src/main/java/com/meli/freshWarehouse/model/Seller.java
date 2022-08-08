package com.meli.freshWarehouse.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Seller {

    public Seller(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotEmpty(message = "Seller name cannot be empty.")
    @Size(max = 50, message = "Seller name can't exceed 50 characters.")
    private String name;

    @OneToMany(mappedBy = "seller")
    @JsonIgnoreProperties("seller")
    private Set<Product> listProduct;


}
