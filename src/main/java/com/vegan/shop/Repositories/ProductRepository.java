package com.vegan.shop.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vegan.shop.Models.Certificate;
import com.vegan.shop.Models.Product;
import com.vegan.shop.Models.Store;

@Repository
public interface ProductRepository extends BaseRepository<Product>{
    List<Product> findByNameContaining(String name);
    List<Product> findDistinctByCertificatesIn(List<Certificate> preferences);
    // List<Product> findByStoresId(Long storeId);
    List<Product> findDistinctByStoresIn(List<Store> Stores);

    @Query("SELECT p FROM Product p JOIN p.stores s WHERE p.name LIKE :name AND s IN (:stores)")
    List<Product> findByNameAndStores(@Param("name") String name, @Param("stores") List<Store> stores);

    @Query("SELECT p FROM Product p JOIN p.categories c WHERE c.id = :categoryId")
    List<Product> findByCategoryId(@Param("categoryId") Long categoryId);
}
