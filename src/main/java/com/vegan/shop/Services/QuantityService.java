package com.vegan.shop.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vegan.shop.Models.Quantity;
import com.vegan.shop.Repositories.QuantityRepository;

@Service
public class QuantityService extends BaseService<Quantity> {

    private final QuantityRepository quantityRepository;

    public QuantityService(QuantityRepository quantityRepository)
    {
        super(quantityRepository);
        this.quantityRepository = quantityRepository;
        
    }
    
    public void saveAll(List<Quantity> quantities) 
    {
    for (Quantity quantity : quantities) {
        quantityRepository.save(quantity);
    }
    }

    public List<Quantity> findQuantityByProductId(Long productId)
    {
        return quantityRepository.findQuantityByProductId(productId);
    }

}
