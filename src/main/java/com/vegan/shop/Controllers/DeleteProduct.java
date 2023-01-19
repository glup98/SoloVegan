package com.vegan.shop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.vegan.shop.Models.Product;
import com.vegan.shop.Services.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DeleteProduct {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}/delete")
    public String askDelete(@PathVariable("id")Long id,Model model){
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "deleteProduct";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id")Long id){
        productService.deleteById(id);
        return "redirect:/dashboard";
    }

}