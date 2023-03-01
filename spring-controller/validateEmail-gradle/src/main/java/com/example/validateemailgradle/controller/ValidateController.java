package com.example.validateemailgradle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("")
public class ValidateController {
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static Pattern pattern;
    private Matcher matcher;
    public ValidateController(){
        pattern = Pattern.compile(EMAIL_REGEX);
    }
    @GetMapping("/")
    public String home(){
        return "home";
    }
    @PostMapping("/validate")
    public String user(@RequestParam("email") String email, ModelMap modelMap){
        boolean isvalid = validate(email);
        if(!isvalid){
            modelMap.addAttribute("message", "Email is invalid");
            return "home";
        }
        modelMap.addAttribute("email", email);
        return "success";
    }
    private boolean validate(String regex){
        matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
