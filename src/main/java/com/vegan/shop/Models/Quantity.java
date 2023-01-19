package com.vegan.shop.Models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="quantities")
@NoArgsConstructor
@Getter
@Setter
public class Quantity extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "nutritional_information_id")
    private NutritionalInformation nutritionalInformation;

    @NotNull(message = "Este campo no puede ser nulo.")
    private String volume;

    @NotNull(message = "Este campo no puede ser nulo.")
    private String portion;

}