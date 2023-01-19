package com.vegan.shop.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vegan.shop.Models.NutritionalInformation;
import com.vegan.shop.Models.Quantity;
import com.vegan.shop.Services.NutritionalInformationService;
import com.vegan.shop.Services.QuantityService;

@Controller
public class CreateNutricionalInformationController {

    @Autowired
    private NutritionalInformationService nutritionalInformationService;

    @Autowired
    private QuantityService quantityService;
    
    @GetMapping("/create/nutriInfo")
    public String createNutriInfo(@ModelAttribute("nutri")NutritionalInformation nutritionalInformation, HttpSession session, Model model)
    {
        Long productId = (Long) session.getAttribute("productId");

        List<NutritionalInformation> allNutritionalInformation = nutritionalInformationService.findAll();
        // Obtener las relaciones Quantity para cada producto
        List <Quantity> quantities = quantityService.findQuantityByProductId(productId);

        model.addAttribute("quantities", quantities);

        model.addAttribute("productId", productId);
        model.addAttribute("allNutritionalInformation",allNutritionalInformation);
        return "createNutri";
    }

    @PostMapping("/create/nutriInfo")
    public String saveNutriInfo(@Valid@ModelAttribute("nutri")NutritionalInformation nutritionalInformation,HttpSession session,BindingResult result){

        if (result.hasErrors()){
            return "createNutri";
        }
        nutritionalInformationService.save(nutritionalInformation);
        return "redirect:/create/nutriInfo";
        

    }
}
