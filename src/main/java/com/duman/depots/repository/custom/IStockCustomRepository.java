package com.duman.depots.repository.custom;

import com.duman.depots.model.dto.StockDTO;
import com.duman.depots.model.dto.StockListDTO;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface IStockCustomRepository {

    List<StockListDTO> findAll();
    List<StockListDTO> findByDepotId(Long depotId);
    List<StockListDTO> findByProductId(Long StockId);
}
