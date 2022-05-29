package com.duman.depots.mapper;

import com.duman.depots.model.dto.DepotDTO;
import com.duman.depots.model.entity.Depot;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepotMapper {
    DepotDTO toDTO(Depot depot);

    List<DepotDTO> toListDTO(List<Depot> depotList);

    Depot toEntity(DepotDTO dto);

    List<Depot> toListEntity(List<DepotDTO> dtoList);

}
