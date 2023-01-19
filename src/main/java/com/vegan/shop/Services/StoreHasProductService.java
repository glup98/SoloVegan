package com.vegan.shop.Services;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.vegan.shop.Models.StoreHasProduct;

import com.vegan.shop.Repositories.StoreHasProductRepository;

@Service
public class StoreHasProductService extends BaseService<StoreHasProduct>
{

    private final StoreHasProductRepository storeHasProductRepository;

    public StoreHasProductService(StoreHasProductRepository storeHasProductRepository)
    {
        super(storeHasProductRepository);
        this.storeHasProductRepository = storeHasProductRepository;
    }

    public List<StoreHasProduct> findStoreHasProductByProductId(Long productId)
    {
        return storeHasProductRepository.findStoreHasProductByProductId(productId);
    }

    public boolean existsByStoreIdAndProductId(@Param("storeId") Long storeId, @Param("productId") Long productId)
    {
        return storeHasProductRepository.existsByStoreIdAndProductId(storeId, productId);
    }

    public List<StoreHasProduct> findByProductId(Long productId)
    {
        return storeHasProductRepository.findByProductId(productId);
    }

}
