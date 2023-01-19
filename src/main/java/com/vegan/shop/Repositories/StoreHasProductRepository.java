package com.vegan.shop.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vegan.shop.Models.StoreHasProduct;

@Repository
public interface StoreHasProductRepository extends BaseRepository<StoreHasProduct>
{
    // Encuentra una lista de StoreHasProduct con todos los que contengan productId especifico
    @Query("SELECT shp FROM StoreHasProduct shp WHERE shp.product.id = :productId")
    List<StoreHasProduct> findStoreHasProductByProductId(Long productId);

    @Query("SELECT COUNT(shp) > 0 FROM StoreHasProduct shp WHERE shp.store.id = :storeId AND shp.product.id = :productId")
    boolean existsByStoreIdAndProductId(@Param("storeId") Long storeId, @Param("productId") Long productId);
    
    @Query( value = "SELECT * FROM shop.stores_has_products shp JOIN stores s ON shp.store_id = s.id WHERE shp.product_id =?1" , nativeQuery= true)
    List<StoreHasProduct> findByProductId(Long id);
}