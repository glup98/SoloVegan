package com.vegan.shop.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vegan.shop.Models.Category;
import com.vegan.shop.Models.Certificate;
import com.vegan.shop.Models.Product;
import com.vegan.shop.Models.StoreHasProduct;
import com.vegan.shop.Models.User;
import com.vegan.shop.Services.CategoryService;
import com.vegan.shop.Services.CertificateService;
import com.vegan.shop.Services.ProductService;
import com.vegan.shop.Services.StoreHasProductService;
import com.vegan.shop.Services.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class UserDashboardController 
{
    private final UserService userService;
    private final CertificateService certificateService;
    private final ProductService productService;
    private final StoreHasProductService storeHasProductService;
    private final CategoryService categoryService;

    @GetMapping("")
    public String userDashboard(Model model, HttpSession session)
    {
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) 
        {   
            User user = userService.findById(userId);
            List <Certificate> userPreferences = user.getPreferences();
            List <Certificate> allCertificates = certificateService.findAll();
            List <Product> productsWithPreferencesUser = productService.findByCertificatesIn(userPreferences);

            // Obtener las relaciones StoreHasProduct para cada producto
            List <StoreHasProduct> allStoreHasProductWithPreference = new ArrayList<>();
            for (Product productWithPreference : productsWithPreferencesUser) 
            {
                List <StoreHasProduct> storeHasProductWithPreference = storeHasProductService.findStoreHasProductByProductId(productWithPreference.getId());
                allStoreHasProductWithPreference.addAll(storeHasProductWithPreference);
            }
            model.addAttribute("allStoreHasProductWithPreference", allStoreHasProductWithPreference);

            List<StoreHasProduct> allStoreHasProdudct = storeHasProductService.findAll();
            model.addAttribute("allStoreHasProdudct",allStoreHasProdudct);

            // Crea una lista de booleanos para almacenar el estado de selección de cada certificado
            List<Boolean> selectionStatus = new ArrayList<>();

            // Recorre todos los certificados y determina si están seleccionados o no
            for (Certificate certificate : allCertificates) 
            {
                boolean selected = certificateService.userBelongsToCertificate(certificate.getId(), userId);
                selectionStatus.add(selected);
            }
            // Agrega la lista de certificados, la lista de preferencias del usuario y la lista de estados de selección al modelo
            model.addAttribute("allCertificates", allCertificates);
            model.addAttribute("userPreferences", userPreferences);
            model.addAttribute("selectionStatus", selectionStatus);
            model.addAttribute("user", user);

            //Mostrar Categorias a la vista
            List<Category> categories = categoryService.findAll();
            model.addAttribute("categories", categories);

            return "userDashboard";
        } 
        else 
        {
            return "redirect:/";
        }
    }

    @PostMapping("")
    public String selectPreferences(@RequestParam("preferences") List<Certificate> preferences, HttpSession session) 
    {
        Long userId = (Long) session.getAttribute("userId");
        User userSession = userService.findById(userId);
        userSession.setPreferences(preferences);
        userService.save(userSession);
        return "redirect:/dashboard";
    }
}
