package com.duman.depots.repository;

import com.duman.depots.model.entity.Stock;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Stock findByProductIdAndDepotId(Long productId, Long depotId);
}
