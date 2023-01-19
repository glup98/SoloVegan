package com.vegan.shop.Controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vegan.shop.Models.Store;
import com.vegan.shop.Models.User;
import com.vegan.shop.Services.StoreService;
import com.vegan.shop.Services.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StoreController 
{
    private final StoreService storeService;
    private final UserService userService;
    
    @GetMapping("/create/store")
    public String createStore(@ModelAttribute ("store") Store store) 
    {
        return "createStore";
    }

    @PostMapping("/create/store")
    public String addStore(@Valid @ModelAttribute ("store") Store store, BindingResult result, HttpSession session) 
    {
        if (result.hasErrors()) 
        {
            return "createStore";
        } 
        else 
        {
            Long adminId = (Long) session.getAttribute("userId");
            User admin = userService.findById(adminId);
            store.setAdmin(admin);
            storeService.save(store);
            Long storeId = store.getId();
            session.setAttribute("storeId", storeId);
        }
        return "redirect:/create/product";
    }
}
