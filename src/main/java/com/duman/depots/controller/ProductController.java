package com.duman.depots.controller;

import com.duman.depots.model.dto.ProductDTO;
import com.duman.depots.service.IProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/product") //pre-path
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping //api/product
    public List<ProductDTO> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}") //api/product/{id}
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @PostMapping //api/product
    public ResponseEntity<?> saveProduct(@RequestBody @Valid ProductDTO dto) {
        return new ResponseEntity<>(productService.saveProduct(dto), HttpStatus.OK);
    }

    @PutMapping("/{id}") //api/product/{id}
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO dto) {
        return new ResponseEntity<>(productService.updateProduct(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}") //api/product/{id}
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.delete(id), HttpStatus.OK);
    }
}
