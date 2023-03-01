package com.example.springmvcform.controller;

import com.example.springmvcform.model.Customer;
import com.example.springmvcform.service.CustomerService;
import com.example.springmvcform.service.ICustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private ICustomerService customerService = new CustomerService();

    @GetMapping("")
    public String loadIndex(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "/customer/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("customer", new Customer());
        return "/customer/create";
    }

    @PostMapping("/save")
    public String save(Customer customer, RedirectAttributes redirect) {
        customer.setId(System.currentTimeMillis() % 10000);
        customerService.save(customer);
        redirect.addFlashAttribute("success", "Added customer successfully!");
        return "redirect:/customer";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/customer/edit";
    }

    @PostMapping("/update")
    public String update(Customer customer, RedirectAttributes redirect) {
        customerService.update(customer.getId(), customer);
        redirect.addFlashAttribute("success", "Updated customer successfully!");
        return "redirect:/customer";
    }
    @GetMapping("/{id}/delete")
    public String deletePage(@PathVariable long id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/customer/delete";
    }
    @PostMapping("/delete")
    public String delete(Customer customer, RedirectAttributes redirect) {
        customerService.remove(customer.getId());
        redirect.addFlashAttribute("success", "Deleted customer successfully!");
        return "redirect:/customer";
    }
    @GetMapping("/{id}/view")
    public String view(@PathVariable long id, Model model){
        model.addAttribute("customer", customerService.findById(id));
        return "/customer/view";
    }
}
