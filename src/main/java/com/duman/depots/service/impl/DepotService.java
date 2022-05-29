package com.duman.depots.service.impl;

import com.duman.depots.exception.GenericException;
import com.duman.depots.exception.errors.ErrorCode;
import com.duman.depots.mapper.DepotMapper;
import com.duman.depots.mapper.StockMapper;
import com.duman.depots.model.dto.DepotDTO;
import com.duman.depots.model.dto.StockDTO;
import com.duman.depots.model.dto.StockListDTO;
import com.duman.depots.model.dto.StockTransferDTO;
import com.duman.depots.model.entity.Depot;
import com.duman.depots.model.enumerated.DepotType;
import com.duman.depots.model.enumerated.Status;
import com.duman.depots.repository.DepotRepository;
import com.duman.depots.repository.StockRepository;
import com.duman.depots.repository.custom.IStockCustomRepository;
import com.duman.depots.service.IDepotService;
import com.duman.depots.service.IStockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
@AllArgsConstructor
public class DepotService implements IDepotService {

    private final DepotRepository depotRepository;

    private final DepotMapper depotMapper;

    private final StockRepository stockRepository;

    private final IStockCustomRepository stockCustomRepository;

    private final StockMapper stockMapper;

    private final IStockService stockService;


    @Override
    public List<DepotDTO> findAll() {
        return depotRepository.findAll().stream().map(depotMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public DepotDTO findById(Long id) {
        return depotMapper.toDTO(depotRepository.findById(id).orElseThrow(() ->
                new GenericException(ErrorCode.ENTITY_NOT_FOUND_EXCEPTION, id, Depot.class.getSimpleName())));
    }

    @Override
    @Transactional
    public DepotDTO saveDepot(DepotDTO depotDTO) {
        Depot depot = depotMapper.toEntity(depotDTO);
        return depotMapper.toDTO(depotRepository.save(depot));
    }

    @Override
    @Transactional
    public DepotDTO updateDepot(Long id, DepotDTO depotDTO) {
        Depot depot = depotRepository.findById(id).orElseThrow(() ->
                new GenericException(ErrorCode.ENTITY_NOT_FOUND_EXCEPTION, id, Depot.class.getSimpleName()));
        depot.setDepotName(depotDTO.getDepotName());
        depot.setDepotType(depotDTO.getDepotType());
        depot.setCity(depotDTO.getCity());
        depot.setLocation(depotDTO.getLocation());
        depot.setStatus(depotDTO.getStatus());
        depot.setCostCenter(depotDTO.getCostCenter());
        if (depot.getDepotType() != DepotType.MAIN_DEPOT && depot.getStatus() == Status.CLOSED) {
            closeDepot(depot.getId());
        }
        return depotMapper.toDTO(depotRepository.save(depot));
    }

    @Override
    @Transactional
    public DepotDTO closeDepot(Long id) {
        Depot depot = depotRepository.findById(id).orElseThrow(() ->
                new GenericException(ErrorCode.ENTITY_NOT_FOUND_EXCEPTION, id, Depot.class.getSimpleName()));
        if (depot.getDepotType() != DepotType.MAIN_DEPOT) {
            StockTransferDTO stockTransferDTO = new StockTransferDTO();
            Depot transferDepot = depotRepository.findByCityAndDepotType(depot.getCity(), DepotType.MAIN_DEPOT).orElseThrow(() ->
                    new GenericException(ErrorCode.ENTITY_NOT_FOUND_EXCEPTION, depot.getCity(), DepotType.MAIN_DEPOT, Depot.class.getSimpleName()));
            if (transferDepot != null)
                stockTransferDTO.setTransferDepotId(transferDepot.getId());
            List<StockListDTO> stkList = stockCustomRepository.findByDepotId(id);
            depot.setStatus(Status.CLOSED);
            List<StockDTO> stockDTOList = stockMapper.toStockDTOList(stkList);
            stockDTOList.forEach(s-> {
                stockService.transferStock(s.getId(), stockTransferDTO);
            });

        } else {
            throw new GenericException(ErrorCode.DEPOT_CLOSE_EXCEPTION);
        }
        return depotMapper.toDTO(depotRepository.save(depot));

    }

    @Override
    public Long delete(Long id) {
        depotRepository.deleteById(id);
        return id;
    }
}
