package com.vegan.shop.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vegan.shop.Models.Quantity;

@Repository
public interface QuantityRepository extends BaseRepository<Quantity> {

    // Encuentra una lista de Quantity con todos los que contengan productId especifico
    @Query("SELECT q FROM Quantity q WHERE q.product.id = :productId")
    List<Quantity> findQuantityByProductId(Long productId);

}
