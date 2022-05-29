package com.duman.depots.model.entity;

import com.duman.depots.model.enumerated.DepotType;
import com.duman.depots.model.enumerated.LocationEnum;
import com.duman.depots.model.enumerated.Status;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Depot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "depotName", nullable = false, length = 100)
    private String depotName;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "depotType", nullable = false)
    private DepotType depotType;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "location", nullable = false)
    private LocationEnum location;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "costCenter", nullable = false, length = 100)
    private String costCenter;


}
