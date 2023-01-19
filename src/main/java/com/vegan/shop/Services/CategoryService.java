package com.vegan.shop.Services;

import org.springframework.stereotype.Service;

import com.vegan.shop.Models.Category;
import com.vegan.shop.Repositories.BaseRepository;

@Service
public class CategoryService extends BaseService<Category>  {

    public CategoryService(BaseRepository<Category> baseRepository) {
        super(baseRepository);
    }
    
}
