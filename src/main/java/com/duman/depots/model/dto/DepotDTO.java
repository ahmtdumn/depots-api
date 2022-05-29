package com.duman.depots.model.dto;

import com.duman.depots.model.enumerated.DepotType;
import com.duman.depots.model.enumerated.LocationEnum;
import com.duman.depots.model.enumerated.Status;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DepotDTO {
    private Long id;

    @NotNull
    private String depotName;

    @NotNull
    private DepotType depotType;

    @NotNull
    private String city;

    @NotNull
    private LocationEnum location;

    @NotNull
    private Status status;

    @NotNull
    private String costCenter;
}
