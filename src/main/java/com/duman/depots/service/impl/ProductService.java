package com.duman.depots.service.impl;

import com.duman.depots.exception.GenericException;
import com.duman.depots.exception.errors.ErrorCode;
import com.duman.depots.mapper.ProductMapper;
import com.duman.depots.model.dto.ProductDTO;
import com.duman.depots.model.entity.Product;
import com.duman.depots.repository.ProductRepository;
import com.duman.depots.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private final ProductMapper productMapper;

    private final ProductRepository productRepository;

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(productMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Long id) {
        return productMapper.toDTO(productRepository.findById(id).orElseThrow(() ->
                new GenericException(ErrorCode.ENTITY_NOT_FOUND_EXCEPTION, id, Product.class.getSimpleName())));
    }

    @Override
    @Transactional
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        return productMapper.toDTO(productRepository.save(product));
    }

    @Override
    @Transactional
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new GenericException(ErrorCode.ENTITY_NOT_FOUND_EXCEPTION, id, Product.class.getSimpleName()));
        product.setName(productDTO.getName());
        product.setSku(productDTO.getSku());
        product.setBarcode(productDTO.getBarcode());
        product.setFrozen(productDTO.isFrozen());
        product.setPrice(productDTO.getPrice());
        return productMapper.toDTO(productRepository.save(product));
    }

    @Override
    public Long delete(Long id) {
        productRepository.deleteById(id);
        return id;
    }
}
