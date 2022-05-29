package com.duman.depots.controller;

import com.duman.depots.model.dto.DepotDTO;
import com.duman.depots.service.IDepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/depot")//pre-path
public class DepotController {
    @Autowired
    private IDepotService depotService;

    @GetMapping //api-depot
    public List<DepotDTO> findAll() {
        return depotService.findAll();
    }

    @GetMapping("/{id}") //api/depot/{id}
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(depotService.findById(id), HttpStatus.OK);
    }

    @PostMapping //api/depot
    public ResponseEntity<?> saveDepot(@RequestBody @Valid DepotDTO dto) {
        return new ResponseEntity<>(depotService.saveDepot(dto), HttpStatus.OK);
    }

    @PutMapping("/{id}") //api/depot/{id}
    public ResponseEntity<?> updateDepot(@PathVariable Long id, @RequestBody @Valid DepotDTO dto) {
        return new ResponseEntity<>(depotService.updateDepot(id, dto), HttpStatus.OK);
    }

    @PutMapping("/close/{id}")
    public ResponseEntity<?> closeDepot(@PathVariable Long id) {
        return new ResponseEntity<>(depotService.closeDepot(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}") //api/depot/{id}
    public ResponseEntity<?> deleteDepot(@PathVariable Long id) {
        return new ResponseEntity<>(depotService.delete(id), HttpStatus.OK);
    }
}
