package com.vegan.shop.Repositories;

import org.springframework.stereotype.Repository;

import com.vegan.shop.Models.Ingredient;

@Repository
public interface  IngredientRepository extends BaseRepository<Ingredient>{

    Ingredient findByIngredientName(String ingredientName);
}