package com.vegan.shop.Models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@Setter
public class Product extends BaseModel {

    @NotNull
    @NotBlank(message = "Este campo no puede estar en blanco.")
	@Size(min = 3, max = 128, message = "Nombre debe tener entre 3 y 128 caracteres")
	private String name; 

    @NotNull
    @NotBlank(message = "Este campo no puede estar en blanco.")
	@Size(min = 5, max= 1000, message = "Descripción no puede tener menos de 5 caracteres")
	private String description;

    private String imgRoute;

    private String portionValue;   

	@ManyToMany(fetch= FetchType.LAZY)
    @JoinTable(
        name = "favorite_products", 
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List <User> users;

	@ManyToMany(fetch= FetchType.LAZY)
    @JoinTable(
        name = "products_has_certificates", 
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "certificate_id")
    )
    private List <Certificate> certificates;

	@ManyToMany(fetch= FetchType.LAZY)
    @JoinTable(
        name = "products_has_ingredients", 
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List <Ingredient> ingredients;

	@ManyToMany(fetch= FetchType.LAZY)
    @JoinTable(
        name = "categories_has_products", 
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List <Category> categories;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "stores_has_products", 
        joinColumns = @JoinColumn(name = "product_id"), 
        inverseJoinColumns = @JoinColumn(name = "store_id")
    )
    private List<Store> stores;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "quantities", 
        joinColumns = @JoinColumn(name = "product_id"), 
        inverseJoinColumns = @JoinColumn(name = "nutritional_information_id")
    )
    private List<NutritionalInformation> nutritionalInformation;
}
