package com.duman.depots.controller;

import com.duman.depots.model.dto.StockDTO;
import com.duman.depots.model.dto.StockListDTO;
import com.duman.depots.model.dto.StockTransferDTO;
import com.duman.depots.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/stock") //pre-path
public class StockController {

    @Autowired
    private IStockService stockService;

    @GetMapping //api/stock
    public List<StockListDTO> findAll() {
        return stockService.findAll();
    }

    @GetMapping("/depot/{id}") //api/stock/depot/{id}
    public List<StockListDTO> findByDepotId(@PathVariable Long id) {
        return stockService.findByDepotId(id);
    }

    @GetMapping("/product/{id}") //api/stock/product/{id}
    public List<StockListDTO> findByProductId(@PathVariable Long id) {
        return stockService.findByProductId(id);
    }

    @PostMapping //api/stock
    public ResponseEntity<?> saveStock(@RequestBody @Valid StockDTO stockDTO) {
        return new ResponseEntity<>(stockService.saveStock(stockDTO), HttpStatus.OK);
    }

    @PutMapping("/transfer/{id}")
    public ResponseEntity<?> transferStock(@PathVariable Long id, @RequestBody @Valid StockTransferDTO stockTransferDTO) {
        return new ResponseEntity<>(stockService.transferStock(id, stockTransferDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}") //api/stock/{id}
    public ResponseEntity<?> updateStock(@PathVariable Long id, @RequestBody @Valid StockDTO stockDTO) {
        return new ResponseEntity<>(stockService.updateStock(id, stockDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}") //api/stock/{id}
    public ResponseEntity<?> deleteStock(@PathVariable Long id) {
        return new ResponseEntity<>(stockService.delete(id), HttpStatus.OK);
    }
}
