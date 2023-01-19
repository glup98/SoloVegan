package com.vegan.shop.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseModel {
    @NotNull
    @NotBlank(message = "Este campo no puede estar en blanco.")
	@Size(min = 3, max = 30, message = "El nombre debe tener entre 3 y 30 caracteres")
	private String firstName;

    @NotNull
    @NotBlank(message = "Este campo no puede estar en blanco.")
	@Size(min = 3, max = 30, message = "El apellido debe tener entre 3 y 30 caracteres")
	private String lastName;

    @NotNull
	@NotBlank(message = "Este campo no puede estar en blanco.")
	@Email(message = "Por favor ingresa un email válido!")
	private String email;
    
    @NotNull
	@NotBlank(message = "Este campo no puede estar en blanco.")
	@Size(min = 8, max = 128, message = "Contraseña debe tener entre 8 y 128 caracteres")
	private String password;

	@Transient
	private String passwordConfirmation;

    
    @ManyToMany(fetch= FetchType.LAZY)
    @JoinTable(
        name = "user_preferences", 
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "certificate_id")
    )
    private List <Certificate> preferences = new ArrayList<>();

    @ManyToMany(fetch= FetchType.LAZY)
    @JoinTable(
        name = "favorite_products", 
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List <Product> products;

    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
    private List <Store> stores;

    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
}
