package com.duman.depots.repository;

import com.duman.depots.model.entity.Depot;
import com.duman.depots.model.enumerated.DepotType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepotRepository extends JpaRepository<Depot, Long> {

    Optional<Depot> findByCityAndDepotType(String city, DepotType depotType);
}
