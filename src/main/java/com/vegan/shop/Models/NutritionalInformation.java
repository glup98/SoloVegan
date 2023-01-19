package com.vegan.shop.Models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "nutritionalInformation")
@NoArgsConstructor
@Getter
@Setter
public class NutritionalInformation extends BaseModel {

    @NotNull
    @NotBlank (message = "Este campo no puede estar en blanco.")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "quantities", 
        joinColumns = @JoinColumn(name = "nutritional_information_id"), 
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )     
    private List<Product> products;
}