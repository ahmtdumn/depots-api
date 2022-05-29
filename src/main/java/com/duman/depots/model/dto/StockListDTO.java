package com.duman.depots.model.dto;

import com.duman.depots.model.enumerated.DepotType;
import com.duman.depots.model.enumerated.Status;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StockListDTO {

    private Long id;

    @NotNull
    private Long productId;

    @NotNull
    private Long depotId;

    @NotNull
    private Double amount;

    private String productName;

    private String sku;

    private Double price;

    private String depotName;

    private String depotCity;

    private DepotType depotType;

    private Status depotStatus;
}
