package com.vegan.shop.Models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.URL;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="stores_has_products")
@NoArgsConstructor
@Getter
@Setter
public class StoreHasProduct extends BaseModel{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "product_id")
    private Product product;
    
    @NotNull
    @Positive
    private int normalPrice;

    @Positive
    private int offerPrice;

    @NotNull
    @NotBlank(message = "Este campo no puede estar en blanco.")
    @URL(message = "Por favor ingrese una URL válida")
    private String imgRouteLinks;
}