package com.vegan.shop.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.vegan.shop.Models.Category;
import com.vegan.shop.Models.Certificate;
import com.vegan.shop.Models.Ingredient;
import com.vegan.shop.Models.NutritionalInformation;
import com.vegan.shop.Models.Product;
import com.vegan.shop.Models.Quantity;
import com.vegan.shop.Models.StoreHasProduct;
import com.vegan.shop.Models.User;
import com.vegan.shop.Repositories.NutricionalInformationRepository;
import com.vegan.shop.Services.NutritionalInformationService;
import com.vegan.shop.Services.ProductService;
import com.vegan.shop.Services.QuantityService;
import com.vegan.shop.Services.StoreHasProductService;
import com.vegan.shop.Services.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ShowController {
    
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    private final StoreHasProductService storeHasProductService;
    private final QuantityService quantityService;

    @GetMapping("/product/{id}")
    public String ShowProduct(@PathVariable("id")Long id,@ModelAttribute ("user") User user,Model model, HttpSession session)
    {
        Long userId = (Long) session.getAttribute("userId");
        User userSession = null;
        if (userId != null) 
        {
        userSession = userService.findById(userId);
        }
        
        Product product = productService.findById(id);
        List<Category> categories = product.getCategories();
        List<Certificate> certificates = product.getCertificates();
        List<Ingredient> ingredients = product.getIngredients();
        List<StoreHasProduct> storeHasProductsList = storeHasProductService.findByProductId(product.getId());
        List <Quantity> quantities = quantityService.findQuantityByProductId(id);

        model.addAttribute("quantities", quantities);
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        model.addAttribute("certificates", certificates);
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("spList", storeHasProductsList);
        model.addAttribute("userSession", userSession);
        model.addAttribute("quantities", quantities);
        
        
        return "showProduct";
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable("id")Long id){
        productService.deleteById(id);
    return "redirect:/dashboard";
    }
}
