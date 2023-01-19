package com.vegan.shop.Controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vegan.shop.Models.Product;
import com.vegan.shop.Models.Store;
import com.vegan.shop.Models.StoreHasProduct;
import com.vegan.shop.Models.User;
import com.vegan.shop.Services.ProductService;
import com.vegan.shop.Services.StoreHasProductService;
import com.vegan.shop.Services.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductStoreController 
{
    
    private final ProductService productService;
    private final UserService userService;
    private final StoreHasProductService storeHasProductService;

    @GetMapping("/store/product")
    public String addProductShop(HttpSession session,Model model,@ModelAttribute("storeHasProduct")StoreHasProduct storeHasProduct)
    {
        Long productId = (Long) session.getAttribute("productId");
        Product product = productService.findById(productId);
        List<Product> lProducts = productService.findAll();

        Long id =(Long) session.getAttribute("userId");
        User user = userService.findById(id);

        List<Store> stores =user.getStores();
        model.addAttribute("product", product);
        model.addAttribute("user", user);
        model.addAttribute("stores", stores);
        model.addAttribute("lProducts", lProducts);
        return "productStore";
    }


    @PostMapping("/store/product")
    public String savePS(@Valid @ModelAttribute("storeHasProduct")StoreHasProduct storeHasProduct,BindingResult result, HttpSession session) 
    {
        if(result.hasErrors())
        {
            return "productStore";
        }
        else
        {
            storeHasProductService.save(storeHasProduct);
        } 
        return "redirect:/dashboard";
    }

}
