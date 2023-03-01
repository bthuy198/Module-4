package com.example.calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("")
public class CalculatorController {
    @GetMapping("/")
    public String loadIndex(){
        return "index";
    }

    @GetMapping("/result")
    public String calculate(@RequestParam String a, String b, String cal, ModelMap map){
        double result = 0;
        switch (cal){
            case "Addition":
                result = Double.parseDouble(a) + Double.parseDouble(b);
                break;
            case "Minus":
                result = Double.parseDouble(a) - Double.parseDouble(b);
                break;
            case "Multiplication":
                result = Double.parseDouble(a) * Double.parseDouble(b);
                break;
            case "Division":
                result = Double.parseDouble(a) / Double.parseDouble(b);
                break;
            default:
                break;
        }
        map.addAttribute("result", result);
        return "index";
    }
}
