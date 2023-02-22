package com.example.convertcurrency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
@Controller
public class ConvertController {
    @GetMapping("/abc")
    public String indfsdf() {
        return "index";
    }


    @PostMapping("/convert")
        public String info(@RequestParam String rate, String usd, Model model) {
            double result = Double.parseDouble(rate) * Double.parseDouble(usd);
            model.addAttribute("result", result);
            return "index";
        }


}
