package com.vegan.shop.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vegan.shop.Models.NutritionalInformation;

import com.vegan.shop.Repositories.NutricionalInformationRepository;

@Service
public class NutritionalInformationService extends BaseService<NutritionalInformation>  {

    private final NutricionalInformationRepository nutricionalInformationRepository;

    public NutritionalInformationService(NutricionalInformationRepository nutricionalInformationRepository) {
        super(nutricionalInformationRepository);
        this.nutricionalInformationRepository = nutricionalInformationRepository;
    }

    public List<NutritionalInformation> findByProductId(Long productId)
    {
        return nutricionalInformationRepository.findByProductId(productId);
    }
}
