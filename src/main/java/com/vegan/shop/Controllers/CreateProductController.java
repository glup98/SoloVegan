package com.vegan.shop.Controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vegan.shop.Models.Product;
import com.vegan.shop.Services.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CreateProductController 
{
    private final ProductService productService;

    @GetMapping("/create/product")
    public String createProduct(@ModelAttribute("product") Product product,HttpSession session)
    {
        return "createProduct";
    }

    @PostMapping("/create/product")
    public String saveProduct(@Valid @ModelAttribute ("product") Product product, BindingResult result, HttpSession session, @RequestParam("productfile")MultipartFile productFile)
    {
        
        if (result.hasErrors()) 
        {
            System.out.println("entre a errores");
            return "createProduct";
        } 
        else 
        {
            System.out.println("entre a guardar el producto");
            productService.save(product);
            Long productId = (Long) product.getId();
            session.setAttribute("productId", productId);
        }
        List<Product> productl = productService.findAll();
        Product product2 = productl.get(productl.size()-1);
        Long id = productl.get(productl.size()-1).getId();
        if(!productFile.isEmpty()){
            String fileName = productFile.getOriginalFilename() ;
            String imgRoute = "/img/"+id;
            File directory = new File("src/main/resources/static"+ imgRoute);
            if(!directory.exists()){
                directory.mkdirs();
            }
            try {
                byte[] bytes = productFile.getBytes();
                BufferedOutputStream outputStream = new BufferedOutputStream(
                    new FileOutputStream(
                        new File(directory.getAbsolutePath() + "/" + fileName)
                    )
                );
                outputStream.write(bytes);
                outputStream.flush();
                outputStream.close();
                System.out.println("se subio el archivo");
                product.setImgRoute(imgRoute+ "/"+fileName);
                productService.save(product2);
            } catch (IOException e) {
                System.out.println("error"+e);
                e.printStackTrace();
            }
        }
        return "redirect:/product/"+id+"/all";
    }
}
