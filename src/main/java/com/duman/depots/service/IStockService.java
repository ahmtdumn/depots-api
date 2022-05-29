package com.duman.depots.service;

import com.duman.depots.model.dto.StockDTO;
import com.duman.depots.model.dto.StockListDTO;
import com.duman.depots.model.dto.StockTransferDTO;
import com.duman.depots.model.entity.Stock;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IStockService {
    List<StockListDTO> findAll();

    List<StockListDTO> findByDepotId(Long depotId);

    List<StockListDTO> findByProductId(Long productId);

    StockDTO findById(Long id);

    @Transactional
    StockDTO saveStock(StockDTO stockDTO);

//    @Transactional
//    StockDTO updateStock(Long id, StockDTO stockDTO);

    @Transactional
    StockDTO transferStock(Long id, StockTransferDTO stockTransferDTO);

    Long delete(Long id);
}
