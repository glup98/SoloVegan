package com.vegan.shop.Controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vegan.shop.Models.User;
import com.vegan.shop.Services.UserService;
import com.vegan.shop.Validator.UserValidator;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController 
{
    private final UserService userService;
    private final UserValidator userValidator; 

    @PostMapping("")
    public String registerUser(@Valid @ModelAttribute ("user") User user, BindingResult result, HttpSession session,Model model) 
    {

        userValidator.validate(user, result);

        if (userService.findByEmail(user.getEmail()) != null) 
        {
            String errorEmail= "Este email ya esta registrado.";
            model.addAttribute("errorEmail", errorEmail);
            return "home";
        }
        if (result.hasErrors()) 
        {
            return "home";
        } 
        else 
        {
            userService.registerUser(user);
            Long userId = user.getId();
            session.setAttribute("userId", userId);
        }
        return "redirect:/dashboard";
    }

}
