package com.vegan.shop.Models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;
import org.springframework.lang.NonNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
public class Category extends BaseModel{
    @NonNull
    @NotBlank(message = "Este campo no puede estar en blanco.")
	private String categoryName;
    @NonNull
    @NotBlank(message = "Este campo no puede estar en blanco.")
    @URL(message = "Por favor ingrese una URL válida")
    private String categoryIcon;

    @ManyToMany(fetch= FetchType.LAZY)
    @JoinTable(
        name = "categories_has_products", 
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List <Product> products;

}

