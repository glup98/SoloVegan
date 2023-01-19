package com.vegan.shop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.vegan.shop.Models.Category;
import com.vegan.shop.Models.Certificate;
import com.vegan.shop.Models.Ingredient;
import com.vegan.shop.Models.NutritionalInformation;
import com.vegan.shop.Models.Product;
import com.vegan.shop.Models.Quantity;
import com.vegan.shop.Services.CategoryService;
import com.vegan.shop.Services.CertificateService;
import com.vegan.shop.Services.IngredientService;
import com.vegan.shop.Services.NutritionalInformationService;
import com.vegan.shop.Services.ProductService;
import com.vegan.shop.Services.QuantityService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CertificateService certificateService;
    @Autowired
    private IngredientService ingredientService;
    @Autowired 
    private NutritionalInformationService nutritionalInformationService;
    @Autowired
    private QuantityService quantityService;
    
    //Esto deberia ser con checkboxes
    //Categories
    @GetMapping("/product/{id}/categories/edit")
    public String giveCategory(@PathVariable("id")Long id,@ModelAttribute("product")Product product,Model model) {
        Product product2 = productService.findById(id);
        List<Category> lCategory = categoryService.findAll();
        model.addAttribute("categoryList", lCategory);
        model.addAttribute("product", product2);
        return "productCategory";
    }
    @PostMapping("/product/{id}/categories/edit")
    public String saveCategories(@PathVariable("id")Long id,@ModelAttribute("product")Product product){
        Product product2 = productService.findById(id);
        product2.setCategories(product.getCategories());
        productService.save(product2);
        return "redirect:/dashboard";
        
    }
    //Certificates
    @GetMapping("/product/{id}/certificates/edit")
    public String giveCertificate(@PathVariable("id")Long id,@ModelAttribute("product")Product product,Model model) {
        Product product2 = productService.findById(id);
        List<Certificate> lCertificates = certificateService.findAll();
        model.addAttribute("product", product2);
        model.addAttribute("certificatesList", lCertificates);
        return "productCertificates";
    }
    @PostMapping("/product/{id}/certificates/edit")
    public String saveCertificates(@PathVariable("id")Long id,@ModelAttribute("product")Product product){
        Product product2 = productService.findById(id);
        product2.setCertificates(product.getCertificates());
        productService.save(product2);
        return "redirect:/dashboard";
        
    }
    
    //Ingredients
    @GetMapping("/product/{id}/ingredients/edit")
    public String giveIngredient(@PathVariable("id")Long id,@ModelAttribute("product")Product product,Model model) {
        Product product2 = productService.findById(id);
        List<Ingredient> lIngredients = ingredientService.findAll();
        model.addAttribute("product", product2);
        model.addAttribute("IngredientsList", lIngredients);
        return "productIngredients";
    }
    @PostMapping("/product/{id}/ingredients/edit")
    public String saveIngredientes(@PathVariable("id")Long id,@ModelAttribute("product")Product product){
        Product product2 = productService.findById(id);
        product2.setIngredients(product.getIngredients());
        productService.save(product2);
        return "redirect:/dashboard";
        
    }

    //nutricional information
    @GetMapping("/product/{productId}/nutri/{nutriId}")
    public String productNI(
        @PathVariable("productId")Long productId,
        @PathVariable("nutriId")Long nutriId, 
        @ModelAttribute("quantity")Quantity quantity,
        HttpSession session,
        Model model)
        {
        Product product = productService.findById(productId);
        NutritionalInformation nutritionalInformation = nutritionalInformationService.findById(nutriId);
        model.addAttribute("product", product);
        model.addAttribute("nutritionalInformation", nutritionalInformation);
        return "productNI";
    }

    //arreglar post
    @PostMapping("/product/{productId}/nutri/{nutriId}")
    public String saveProductNI(
        @PathVariable("productId")Long productId, 
        @PathVariable("nutriId")Long nutriId, 
        @Valid @ModelAttribute("quantity")Quantity quantity,
        BindingResult result,
        HttpSession session,
        Model model) 
        {
        if(result.hasErrors()){
            return "productNI";
        }
        else
        {
            Product product = productService.findById(productId);
            NutritionalInformation nutritionalInformation = nutritionalInformationService.findById(nutriId);
            quantity.setProduct(product);
            quantity.setNutritionalInformation(nutritionalInformation);
            quantityService.save(quantity);
        }
        
        return "redirect:/create/nutriInfo";
    }

    //Luego del create product
    @GetMapping("/product/{id}/all")
    public String giveAll(@PathVariable("id")Long id,@ModelAttribute("product")Product product,Model model) {
        Product product2 = productService.findById(id);
        List<Category> lCategory = categoryService.findAll();
        List<Certificate> lCertificates = certificateService.findAll();
        List<Ingredient> lIngredients = product2.getIngredients();
        model.addAttribute("product", product2);
        model.addAttribute("categoryList", lCategory);
        model.addAttribute("certificatesList", lCertificates);
        model.addAttribute("ingredientsList", lIngredients);
        return "productAll";
    }
    @PostMapping("/product/{id}/all")
    public String saveAll(@PathVariable("id")Long id,@ModelAttribute("product")Product product){
        Product product2 = productService.findById(id);
        product2.setCategories(product.getCategories());
        product2.setCertificates(product.getCertificates());
        productService.save(product2);
        return "redirect:/create/nutriInfo";
        
    }
}