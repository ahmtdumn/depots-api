package com.duman.depots.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "sku", nullable = false, length = 100)
    private String sku;

    @Column(name = "barcode", nullable = false, length = 100)
    private String barcode;

    @Column(name = "isFrozen", nullable = false)
    private boolean isFrozen;

    @Column(name = "price", nullable = false)
    private Double price;
}
