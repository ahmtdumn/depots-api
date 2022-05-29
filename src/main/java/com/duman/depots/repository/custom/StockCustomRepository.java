package com.duman.depots.repository.custom;

import com.duman.depots.model.dto.StockListDTO;
import com.duman.depots.model.entity.QDepot;
import com.duman.depots.model.entity.QProduct;
import com.duman.depots.model.entity.QStock;
import com.duman.depots.model.entity.Stock;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StockCustomRepository extends QuerydslRepositorySupport implements IStockCustomRepository {

    public StockCustomRepository() {
        super(Stock.class);
    }

    @Override
    public List<StockListDTO> findAll() {
        QStock stock = QStock.stock;
        QProduct product = QProduct.product;
        QDepot depot = QDepot.depot;
        JPQLQuery<StockListDTO> query = from(stock)
                .select(Projections.bean(StockListDTO.class,
                                stock.id,
                                product.id.as("productId"),
                                depot.id.as("depotId"),
                                stock.amount,
                                product.name.as("productName"),
                                product.sku,
                                product.price,
                                depot.depotName.as("depotName"),
                                depot.city.as("depotCity"),
                                depot.depotType.as("depotType"),
                                depot.status.as("depotStatus")
                        )
                ).innerJoin(product).on(product.id.eq(stock.product.id))
                .innerJoin(depot).on(depot.id.eq(stock.depot.id));
        return query.fetch();
    }

    @Override
    public List<StockListDTO> findByDepotId(Long depotId) {
        QStock stock = QStock.stock;
        QProduct product = QProduct.product;
        QDepot depot = QDepot.depot;
        JPQLQuery<StockListDTO> query = from(stock)
                .select(Projections.bean(StockListDTO.class,
                                stock.id,
                                product.id.as("productId"),
                                depot.id.as("depotId"),
                                stock.amount,
                                product.name.as("productName"),
                                product.sku,
                                product.price,
                                depot.depotName.as("depotName"),
                                depot.city.as("depotCity"),
                                depot.depotType.as("depotType"),
                                depot.status.as("depotStatus")
                        )
                ).innerJoin(product).on(product.id.eq(stock.product.id))
                .innerJoin(depot).on(depot.id.eq(stock.depot.id))
                .where(stock.depot.id.eq(depotId));
        return query.fetch();
    }

    @Override
    public List<StockListDTO> findByProductId(Long stockId) {
        QStock stock = QStock.stock;
        QProduct product = QProduct.product;
        QDepot depot = QDepot.depot;
        JPQLQuery<StockListDTO> query = from(stock)
                .select(Projections.bean(StockListDTO.class,
                                stock.id,
                                product.id.as("productId"),
                                depot.id.as("depotId"),
                                stock.amount,
                                product.name.as("productName"),
                                product.sku,
                                product.price,
                                depot.depotName.as("depotName"),
                                depot.city.as("depotCity"),
                                depot.depotType.as("depotType"),
                                depot.status.as("depotStatus")
                        )
                ).innerJoin(product).on(product.id.eq(stock.product.id))
                .innerJoin(depot).on(depot.id.eq(stock.depot.id))
                .where(stock.product.id.eq(stockId));
        return query.fetch();
    }
}
