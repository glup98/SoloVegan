package com.vegan.shop.Models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="ingredients")
@NoArgsConstructor
@Getter
@Setter
public class Ingredient extends BaseModel{

    @NotNull
    @NotBlank(message ="Este campo no puede estar en blanco.")
    private String ingredientName;
    
    @ManyToMany(fetch= FetchType.LAZY)
    @JoinTable(
        name = "products_has_ingredients", 
        joinColumns = @JoinColumn(name = "ingredient_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List <Product> products;
}