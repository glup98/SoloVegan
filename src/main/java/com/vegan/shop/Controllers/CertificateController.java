package com.vegan.shop.Controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vegan.shop.Models.Certificate;
import com.vegan.shop.Services.CertificateService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/create")
public class CertificateController 
{
    private final CertificateService certificateService;

    @GetMapping("/certificate")
    public String createCertificate(@ModelAttribute("certificate")Certificate certificate, HttpSession session)
    {
        return "createCertificate";
    }
    
    @PostMapping("/certificate")
    public String saveCertificate(@Valid @ModelAttribute("certificate")Certificate certificate,BindingResult result, HttpSession session)
    {
        if (result.hasErrors()) 
        {
            return "createCertificate";
        } 
        else 
        {
            certificateService.save(certificate);
        }
        
        Long productId = (Long) session.getAttribute("productId");
        return "redirect:/product/"+productId+"/all";
    }
}
