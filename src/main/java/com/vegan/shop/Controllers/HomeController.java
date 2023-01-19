package com.vegan.shop.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vegan.shop.Models.Category;
import com.vegan.shop.Models.Certificate;
import com.vegan.shop.Models.Product;
import com.vegan.shop.Models.StoreHasProduct;
import com.vegan.shop.Models.User;
import com.vegan.shop.Services.CategoryService;
import com.vegan.shop.Services.ProductService;
import com.vegan.shop.Services.StoreHasProductService;
import com.vegan.shop.Services.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class HomeController {

    private final UserService userService;
    private final ProductService productService;
    private final StoreHasProductService storeHasProductService;
    private final CategoryService categoryService;

    String errorLog = "";

    @GetMapping("")
    public String login(@ModelAttribute ("user") User user,HttpSession session,Model model) 
    {   
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("userId", userId);
        if (userId != null) 
        {
            User userSession = userService.findById(userId);
            model.addAttribute("userSession", userSession);
            List <Certificate> userPreferences = userSession.getPreferences();
            List <Product> productsWithPreferencesUser = productService.findByCertificatesIn(userPreferences);
            model.addAttribute("userPreferences", userPreferences);
            List <StoreHasProduct> allStoreHasProductWithPreference = new ArrayList<>();
            
            for (Product productWithPreference : productsWithPreferencesUser) 
            {
                List <StoreHasProduct> storeHasProductWithPreference = storeHasProductService.findStoreHasProductByProductId(productWithPreference.getId());
                allStoreHasProductWithPreference.addAll(storeHasProductWithPreference);
            }
            model.addAttribute("allStoreHasProductWithPreference", allStoreHasProductWithPreference);
        }
        model.addAttribute("errorLog", errorLog);
        errorLog = "";  

        List<StoreHasProduct> allStoreHasProdudct = storeHasProductService.findAll();
        model.addAttribute("allStoreHasProdudct",allStoreHasProdudct);

        //Mostrar Categorias a la vista
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        List <Product> productImg = productService.findAll();
        model.addAttribute("productImg", productImg);
        return "home";

    }

    //Inicio de sesion
    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password,HttpSession session) 
    {
        if (userService.authenticateUser(email, password)) 
        {
            User user = userService.findByEmail(email);
            Long userId = user.getId();
            session.setAttribute("userId", userId);
            return "redirect:/dashboard";
        } 
        else 
        {
            errorLog = "Email o contraseña incorrecta.";
        }
        return "redirect:/";
    } 
}