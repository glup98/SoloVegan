package com.vegan.shop.Controllers;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vegan.shop.Models.Certificate;
import com.vegan.shop.Models.User;
import com.vegan.shop.Services.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/preferences")
public class PreferenceController 
{
    private final UserService userService;

    @PostMapping("")
    public String selectPreferences(@RequestParam("preferences") List<Certificate> preferences, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        User userSession = userService.findById(userId);
        if (preferences.isEmpty()) 
        {
            // El usuario no ha seleccionado ninguna preferencia
            userSession.setPreferences(null);
        } else {
            userSession.setPreferences(preferences);
        }
        userService.save(userSession);
        return "redirect:/dashboard";
    }
}