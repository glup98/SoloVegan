package com.vegan.shop.Services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.vegan.shop.Models.Certificate;
import com.vegan.shop.Models.Product;
import com.vegan.shop.Models.Store;
import com.vegan.shop.Models.StoreHasProduct;
import com.vegan.shop.Repositories.ProductRepository;

@Service
public class ProductService extends BaseService <Product>
{
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository)
    {
        super(productRepository);
        this.productRepository = productRepository;
    }

    public List<Product> findByNameContaining(String name)
    {
        return productRepository.findByNameContaining(name);
    }

    public List <Product> findByCertificatesIn(List<Certificate> preferences)
    {
        return productRepository.findDistinctByCertificatesIn(preferences);
    }

    public List<Product> findDistinctByStoresIn(List<Store> stores) 
    {
        return productRepository.findDistinctByStoresIn(stores);
    }

    public List<Product> findByNameAndStores(String name,List<Store> stores) 
    {
        return productRepository.findByNameAndStores(name, stores);
    }

    public List<Product> findByCategoryId(@Param("categoryId") Long categoryId)
    {
        return productRepository.findByCategoryId(categoryId);
    }
    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

        public List<StoreHasProduct> removeDuplicateProducts(List<StoreHasProduct> storeHasProductsList) {
        Set<Long> ids = new HashSet<>();
        List<StoreHasProduct> result = new ArrayList<>();
        for (StoreHasProduct storeHasProduct : storeHasProductsList)
        {
            if(!ids.contains(storeHasProduct.getProduct().getId())) 
            {
                ids.add(storeHasProduct.getProduct().getId());
                result.add(storeHasProduct);
            }
        }
        return result;
    }
}
