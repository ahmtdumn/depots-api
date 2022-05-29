package com.duman.depots.service;

import com.duman.depots.model.dto.DepotDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IDepotService {
    List<DepotDTO> findAll();

    DepotDTO findById(Long id);

    @Transactional
    DepotDTO saveDepot(DepotDTO depotDTO);

    @Transactional
    DepotDTO updateDepot(Long id, DepotDTO depotDTO);

    @Transactional
    DepotDTO closeDepot(Long id);

    Long delete(Long id);
}
