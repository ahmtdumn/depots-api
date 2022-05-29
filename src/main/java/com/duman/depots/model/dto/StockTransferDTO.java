package com.duman.depots.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StockTransferDTO {
    private Long id;

    @NotNull
    private Long transferDepotId;
}
