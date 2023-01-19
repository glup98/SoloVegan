package com.vegan.shop.Controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vegan.shop.Models.Ingredient;
import com.vegan.shop.Models.Product;
import com.vegan.shop.Services.IngredientService;
import com.vegan.shop.Services.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IngredientController 
{
    private final IngredientService ingredientService;
    private final ProductService productService;

    @GetMapping("/create/ingredient")
    public String createIngredient(@ModelAttribute("ingredient")Ingredient ingredient, HttpSession session){

        return "createIngredient";
    }
    
    @PostMapping("/create/ingredient")
    public String saveingredient(HttpSession session,@RequestParam ("enterIngredients") String enterIngredients, Model model){
        List <Ingredient> ingredientsList = new ArrayList<>();
        List<String> enterIngredientsList  = Arrays.asList(enterIngredients.split(","));
        Long productId = (Long) session.getAttribute("productId");
        Product product = productService.findById(productId);
        for (String enterIngredient : enterIngredientsList) 
        {
            Ingredient optionalIngredient = ingredientService.findByIngredientName(enterIngredients);

                if (optionalIngredient == null) 
                {
                    Ingredient ingredient =ingredientService.setIngredientName(enterIngredient);
                    ingredientService.save(ingredient);
                    ingredientsList.add(ingredient);
                }
                else
                {
                    ingredientsList.add(optionalIngredient);
                }
        }
        product.setIngredients(ingredientsList);
        productService.save(product);

        return "redirect:/product/"+productId+"/all"; 
    }
    

    // @PostMapping("/create/ingredient")
    // public String saveingredient(@Valid @ModelAttribute("ingredient")Ingredient ingredient,BindingResult result,HttpSession session){
    //     if (result.hasErrors()) 
    //     {
    //         return "createIngredient";
    //     } 
    //     else 
    //     {
    //         ingredientService.save(ingredient);
    //     }
    //     Long productId = (Long) session.getAttribute("productId");
    //     return "redirect:/product/"+productId+"/all"; 
    // }
    
}