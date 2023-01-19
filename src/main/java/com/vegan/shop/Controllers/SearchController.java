package com.vegan.shop.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vegan.shop.Models.Category;
import com.vegan.shop.Models.Product;
import com.vegan.shop.Models.Store;
import com.vegan.shop.Models.StoreHasProduct;
import com.vegan.shop.Models.User;
import com.vegan.shop.Services.CategoryService;
import com.vegan.shop.Services.ProductService;
import com.vegan.shop.Services.StoreHasProductService;
import com.vegan.shop.Services.StoreService;
import com.vegan.shop.Services.UserService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController
{
    private final ProductService productService;
    private final StoreService storeService;
    private final UserService userService;
    private final StoreHasProductService storeHasProductService;
    private final CategoryService categoryService;

    String errorLog = "";

    @PostMapping("")
    public String searchResult(
        @RequestParam(value="name",required = false) String name,
        @RequestParam(value = "storesSelected",required = false) List<Store> storesSelected,
        @ModelAttribute ("user") User user,
        HttpSession session,
        Model model)
    {
        List<Product> productsSearch = productService.findByNameContaining(name);
        List<Store> allStores = storeService.findAll();
        List<Product> storeProducts = new ArrayList<>();

        if (storesSelected==null || storesSelected.isEmpty())
        {
            // Obtener las relaciones StoreHasProduct para cada producto
            List <StoreHasProduct> allStoreHasProductSearch = new ArrayList<>();
            for (Product productSearch : productsSearch)
            {
                List <StoreHasProduct> storeHasProductsSearch = storeHasProductService.findStoreHasProductByProductId(productSearch.getId());
                allStoreHasProductSearch.addAll(storeHasProductsSearch);
            }

            model.addAttribute("allStoreHasProductSearch", allStoreHasProductSearch);
        }
        else
        {
            for (Store store : storesSelected)
            {
                for (Product product : store.getProducts())
                {
                    storeProducts.add(product);
                }
            }

            // Este método modifica la lista que lo llama eliminando todos los elementos que no están contenidos en la lista especificada como argumento.
            List<Product> commonProducts = new ArrayList<>(productsSearch);
            commonProducts.retainAll(storeProducts);

            // Obtener las relaciones StoreHasProduct para cada producto
            List <StoreHasProduct> allStoreHasProductSearchFilter = new ArrayList<>();
            for (Product productFilter : commonProducts)
            {
                List <StoreHasProduct> storeHasProductsSearch = storeHasProductService.findStoreHasProductByProductId(productFilter.getId());
                allStoreHasProductSearchFilter.addAll(storeHasProductsSearch);
            }
            allStoreHasProductSearchFilter = productService.removeDuplicateProducts(allStoreHasProductSearchFilter);
            model.addAttribute("allStoreHasProductSearch", allStoreHasProductSearchFilter);
        }

        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) 
        {
            User userSession = userService.findById(userId);
            model.addAttribute("userSession", userSession);
        }
        model.addAttribute("errorLog", errorLog);
        errorLog = "";

        //Mostrar Categorias a la vista
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        model.addAttribute("storesSelected", storesSelected);
        model.addAttribute("allStores", allStores);
        model.addAttribute("name",name);
        return "searchProduct";
    }

}