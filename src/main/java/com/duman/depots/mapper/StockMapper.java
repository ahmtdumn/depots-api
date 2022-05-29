package com.duman.depots.mapper;

import com.duman.depots.model.dto.StockDTO;
import com.duman.depots.model.dto.StockListDTO;
import com.duman.depots.model.entity.Stock;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockMapper {
    StockDTO toDTO(Stock stock);

    List<StockDTO> toListDTO(List<Stock> stockList);

    Stock toEntity(StockDTO dto);

    List<StockDTO> toStockDTOList(List<StockListDTO> stockListDTOList);

    List<Stock> toListEntity(List<StockDTO> dtoList);
}
