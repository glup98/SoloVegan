package com.vegan.shop.Controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vegan.shop.Models.Category;
import com.vegan.shop.Models.Product;
import com.vegan.shop.Models.StoreHasProduct;
import com.vegan.shop.Models.User;
import com.vegan.shop.Services.CategoryService;
import com.vegan.shop.Services.ProductService;
import com.vegan.shop.Services.StoreHasProductService;
import com.vegan.shop.Services.UserService;

import lombok.RequiredArgsConstructor;





@Controller
@RequiredArgsConstructor
public class CategoryController
{
    private final CategoryService categoryService;
    private final ProductService productService;
    private final UserService userService;
    private final StoreHasProductService storeHasProductService;
    String errorLog = "";

    @GetMapping("/create/category")
    public String createCategory(@ModelAttribute("category") Category category,HttpSession session)
    {
        return "createCategory";
    }

    @PostMapping("/create/category")
    public String saveCategory(@Valid @ModelAttribute("category")Category category,BindingResult result,HttpSession session)
    {
        if (result.hasErrors()) 
        {
            return "createCategory";
        } 
        else 
        {
            
            categoryService.save(category);
        }
        //
        
        Long productId = (Long) session.getAttribute("productId");
        return "redirect:/product/"+productId+"/all"; 
    }

    // Mostrar Categorias
    @GetMapping("/category/{categoryId}")
    public String showCategory(@ModelAttribute ("user") User user, Model model, @PathVariable ("categoryId") Long categoryId, HttpSession session) 
    {
        Category category = categoryService.findById(categoryId);
        model.addAttribute("category", category);

        List <Product> productsInCategory = productService.findByCategoryId(categoryId);

        // Obtener las relaciones StoreHasProduct para cada producto
        List <StoreHasProduct> allStoreHasProductsInCategory = new ArrayList<>();
        for (Product product : productsInCategory) 
        {
            List <StoreHasProduct> storeHasProductsInCategory = storeHasProductService.findStoreHasProductByProductId(product.getId());
            allStoreHasProductsInCategory.addAll(storeHasProductsInCategory);
        }
        model.addAttribute("allStoreHasProductsInCategory", allStoreHasProductsInCategory);

        // Necesario para cargar correctamente el navbar
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("userId", userId);
        if (userId != null) 
        {
            User userSession = userService.findById(userId);
            model.addAttribute("userSession", userSession);
        }
        //Mostrar Categorias a la vista
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        model.addAttribute("errorLog", errorLog);
        errorLog = "";

        return "showCategory";
    }

}
