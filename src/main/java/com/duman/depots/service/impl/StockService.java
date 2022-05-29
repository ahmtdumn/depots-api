package com.duman.depots.service.impl;

import com.duman.depots.exception.GenericException;
import com.duman.depots.exception.errors.ErrorCode;
import com.duman.depots.mapper.StockMapper;
import com.duman.depots.model.dto.StockDTO;
import com.duman.depots.model.dto.StockListDTO;
import com.duman.depots.model.dto.StockTransferDTO;
import com.duman.depots.model.entity.Depot;
import com.duman.depots.model.entity.Product;
import com.duman.depots.model.entity.Stock;
import com.duman.depots.model.enumerated.DepotType;
import com.duman.depots.model.enumerated.Status;
import com.duman.depots.repository.DepotRepository;
import com.duman.depots.repository.ProductRepository;
import com.duman.depots.repository.StockRepository;
import com.duman.depots.repository.custom.IStockCustomRepository;
import com.duman.depots.service.IStockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockService implements IStockService {

    private final StockMapper stockMapper;

    private final StockRepository stockRepository;

    private final DepotRepository depotRepository;

    private final ProductRepository productRepository;

    private final IStockCustomRepository stockCustomRepository;

    @Override
    public List<StockListDTO> findAll() {
        List<StockListDTO> stockListDTOList = stockCustomRepository.findAll();
        return stockListDTOList;
    }

    @Override
    public List<StockListDTO> findByDepotId(Long depotId) {
        List<StockListDTO> stockListDTOList = stockCustomRepository.findByDepotId(depotId);
        return stockListDTOList;
    }

    @Override
    public List<StockListDTO> findByProductId(Long productId) {
        List<StockListDTO> stockListDTOList = stockCustomRepository.findByProductId(productId);
        return stockListDTOList;    }

    @Override
    public StockDTO findById(Long id) {
        return stockMapper.toDTO(stockRepository.findById(id).orElseThrow(() ->
                new GenericException(ErrorCode.ENTITY_NOT_FOUND_EXCEPTION, id, Stock.class.getSimpleName())));
    }

    @Override
    @Transactional
    public StockDTO saveStock(StockDTO stockDTO) {
        Stock stock = stockMapper.toEntity(stockDTO);
        Depot depot = depotRepository.findById(stockDTO.getDepotId()).orElseThrow(() ->
                new GenericException(ErrorCode.ENTITY_NOT_FOUND_EXCEPTION, stockDTO.getDepotId(), Depot.class.getSimpleName()));
        if (depot != null)
            stock.setDepot(depot);
        stock.setProduct(productRepository.findById(stockDTO.getProductId()).orElseThrow(() ->
                new GenericException(ErrorCode.ENTITY_NOT_FOUND_EXCEPTION, stockDTO.getProductId(), Product.class.getSimpleName())));
        if (depot.getDepotType() != DepotType.MAIN_DEPOT) {
            throw new GenericException(ErrorCode.STOCK_DEPOT_EXCEPTION);
        }
        Stock stk = stockRepository.findByProductIdAndDepotId(stockDTO.getProductId(), stockDTO.getDepotId());
        if (stk != null) {
            stk.setAmount(stk.getAmount() + stock.getAmount());
            stockRepository.save(stk);
        } else {
            stockRepository.save(stock);
        }
        return stockMapper.toDTO(stock);
    }

//    @Override
//    @Transactional
//    public StockDTO updateStock(Long id, StockDTO stockDTO) {
//        Stock stock = stockRepository.findById(id).orElseThrow(() ->
//                new GenericException(ErrorCode.ENTITY_NOT_FOUND_EXCEPTION, id, Stock.class.getSimpleName()));
//        stock.setDepot(depotRepository.findById(stockDTO.getDepotId()).orElseThrow(() ->
//                new GenericException(ErrorCode.ENTITY_NOT_FOUND_EXCEPTION, stockDTO.getDepotId(), Depot.class.getSimpleName())));
//        stock.setProduct(productRepository.findById(stockDTO.getProductId()).orElseThrow(() ->
//                new GenericException(ErrorCode.ENTITY_NOT_FOUND_EXCEPTION, stockDTO.getProductId(), Product.class.getSimpleName())));
//        if (stock.getDepot().getDepotType() != DepotType.MAIN_DEPOT) {
//            throw new GenericException(ErrorCode.STOCK_DEPOT_EXCEPTION);
//        }
//        return stockMapper.toDTO(stockRepository.save(stock));
//    }

    @Override
    @Transactional
    public StockDTO transferStock(Long id, StockTransferDTO stockTransferDTO) {
        Stock stock = stockRepository.findById(id).orElseThrow(() ->
                new GenericException(ErrorCode.ENTITY_NOT_FOUND_EXCEPTION, id, Stock.class.getSimpleName()));
        Depot transferDepot = depotRepository.findById(stockTransferDTO.getTransferDepotId()).orElseThrow(() ->
                new GenericException(ErrorCode.ENTITY_NOT_FOUND_EXCEPTION, stockTransferDTO.getTransferDepotId(), Depot.class.getSimpleName()));
        if (transferDepot.getStatus() == Status.CLOSED) {
            throw new GenericException(ErrorCode.DEPOT_CLOSED_EXCEPTION);
        }
        if (stock.getDepot().getCity().equalsIgnoreCase(transferDepot.getCity())) {
            stock.setDepot(transferDepot);
        } else if (!stock.getDepot().getCity().equalsIgnoreCase(transferDepot.getCity()) && stock.getDepot().getDepotType() == DepotType.MAIN_DEPOT) {
            stock.setDepot(transferDepot);
        } else {
            throw new GenericException(ErrorCode.STOCK_TRANSFER_EXCEPTION);
        }
        return stockMapper.toDTO(stockRepository.save(stock));
    }

    @Override
    public Long delete(Long id) {
        stockRepository.deleteById(id);
        return id;
    }
}
