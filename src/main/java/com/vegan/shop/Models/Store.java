package com.vegan.shop.Models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stores")
@NoArgsConstructor
@Getter
@Setter
public class Store extends BaseModel{
    @NotNull
    @NotBlank(message = "Este campo no puede estar en blanco.")
	@Size(min = 3, max = 128, message = "EL nombre debe tener entre 3 y 128 caracteres")
	private String name; 

    @ManyToOne
    @JoinColumn(name="admin_id")
    private User admin;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "stores_has_products", 
        joinColumns = @JoinColumn(name = "store_id"), 
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )     
    private List<Product> products;
}