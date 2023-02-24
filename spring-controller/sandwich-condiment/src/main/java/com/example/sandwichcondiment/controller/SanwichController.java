package com.example.sandwichcondiment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SanwichController {
    @GetMapping("/e")
    public String loadIndex(){
        return "index";
    }
    @RequestMapping("/save")
    public String save(@RequestParam("condiment") String[] condiment, ModelMap map){
        map.addAttribute("condiment", condiment);
        return "save";
    }

}
