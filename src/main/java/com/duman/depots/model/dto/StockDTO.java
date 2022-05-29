package com.duman.depots.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StockDTO {
    private Long id;

    @NotNull
    private Long depotId;

    @NotNull
    private Long productId;

    @NotNull
    private Double amount;
}
