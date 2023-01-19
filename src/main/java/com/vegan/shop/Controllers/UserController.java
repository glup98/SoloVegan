package com.vegan.shop.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vegan.shop.Models.User;
import com.vegan.shop.Services.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class UserController 
{
    private final UserService userService;
    // invalidar la sesión
    // redireccionar a la página de inicio de sesión.
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userId");
        return "redirect:/";
    }

    @PostMapping("/editUser")
    public String editUser(@ModelAttribute ("user") User user, HttpSession session) 
    {
        Long userId=  (Long) session.getAttribute("userId");
        User userSession = userService.findById(userId);

        userSession.setFirstName(user.getFirstName());
        userSession.setLastName(user.getLastName());
        userSession.setEmail(user.getEmail());
        userSession.setPassword(user.getPassword());

        userService.registerUser(userSession);
        return "userDashboard";
    }
}
