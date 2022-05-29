package com.duman.depots.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductDTO {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String sku;

    @NotNull
    private String barcode;

    @NotNull
    private boolean isFrozen;

    @NotNull
    private Double price;
}
