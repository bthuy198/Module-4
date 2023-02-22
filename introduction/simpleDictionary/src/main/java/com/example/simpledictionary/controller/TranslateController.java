package com.example.simpledictionary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TranslateController {
    @GetMapping("/abc")
    public String form(){
        return "index";
    }

    @PostMapping("/translate")
    public String translate(@RequestParam String english, Model model){
        Map<String, String> map = new HashMap<>();

        map.put("pencil", "bút chì");
        map.put("book", "sách");
        map.put("house", "ngôi nhà");
        map.put("store", "cửa hàng");

        String result = map.get(english);

        if(result == null){
            result = "Not found";
        }

        model.addAttribute("result", result);
        return "index";
    }
}
