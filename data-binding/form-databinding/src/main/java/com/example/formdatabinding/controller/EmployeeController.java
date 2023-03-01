package com.example.formdatabinding.controller;

import com.example.formdatabinding.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("employee/")
public class EmployeeController {
    @RequestMapping(value="showForm", method = RequestMethod.GET)
    public String showForm(ModelMap model){
        model.addAttribute("employee", new Employee());
        return "employee/create";
    }
}
