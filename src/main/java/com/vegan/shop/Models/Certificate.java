package com.vegan.shop.Models;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "certificates")
@NoArgsConstructor
@Getter
@Setter
public class Certificate extends BaseModel{
    @NotNull
    @NotBlank(message = "Este campo no puede estar en blanco.")
    private String typeCertificate;

    @ManyToMany(fetch= FetchType.LAZY)
    @JoinTable(
        name = "user_preferences", 
        joinColumns = @JoinColumn(name = "certificate_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List <User> users;

    @ManyToMany(fetch= FetchType.LAZY)
    @JoinTable(
        name = "products_has_certificates", 
        joinColumns = @JoinColumn(name = "certificate_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List <Product> products;
}