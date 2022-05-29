package com.duman.depots.mapper;

import com.duman.depots.model.dto.ProductDTO;
import com.duman.depots.model.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDTO(Product product);

    List<ProductDTO> toListDTO(List<Product> productList);

    Product toEntity(ProductDTO dto);

    List<Product> toListEntity(List<ProductDTO> dtoList);
}
