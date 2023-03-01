package com.example.springuploadfile.controller;

import com.example.springuploadfile.model.Product;
import com.example.springuploadfile.service.IProductService;
import com.example.springuploadfile.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private IProductService productService = new ProductService();
    @RequestMapping("")
    public String index(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "index";
    }
}
