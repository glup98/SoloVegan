package com.vegan.shop.Services;

import org.springframework.stereotype.Service;

import com.vegan.shop.Models.Ingredient;
import com.vegan.shop.Repositories.BaseRepository;
import com.vegan.shop.Repositories.IngredientRepository;

@Service
public class IngredientService extends BaseService <Ingredient>{

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        super(ingredientRepository);
        this.ingredientRepository = ingredientRepository;
    }

    // Creando un modelo Ingredient a partir de un atributo ingredienteName
    public Ingredient setIngredientName(String ingredientName) {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName(ingredientName);
        return ingredient;
    }

    // Encontrando un modelo Ingrediente a partir del atributo ingredienteName
    public Ingredient findByIngredientName(String ingredientName) {
        Ingredient optionalIngredient = ingredientRepository.findByIngredientName(ingredientName);
        if(optionalIngredient != null) {
            return optionalIngredient;
        } else {
            return null;
        }
    }

    
}