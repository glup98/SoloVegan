package com.vegan.shop.Services;

import org.springframework.stereotype.Service;

import com.vegan.shop.Models.Store;
import com.vegan.shop.Repositories.BaseRepository;

@Service
public class StoreService extends BaseService<Store> 
{

    public StoreService(BaseRepository<Store> baseRepository) 
    {
        super(baseRepository);
    }

}
