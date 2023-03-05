package com.example.banking.controller;

import com.example.banking.model.Customer;
import com.example.banking.model.Deposit;
import com.example.banking.model.Transfer;
import com.example.banking.model.Withdraw;
import com.example.banking.service.customer.ICustomerService;
import com.example.banking.service.deposit.IDepositService;
import com.example.banking.service.transfer.ITransferService;
import com.example.banking.service.withdraw.IWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ITransferService transferService;
    @Autowired
    private IDepositService depositService;
    @Autowired
    IWithdrawService withdrawService;
    @GetMapping
    public String showList(Model model){
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customer/list";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model){
        model.addAttribute("customer", new Customer());
        return "customer/create";
    }
    @PostMapping("/create")
    public String createCustomer(@Validated @ModelAttribute Customer customer, BindingResult bindingResult, Model model){
        if(bindingResult.hasFieldErrors()){
            model.addAttribute("errors", true);
            model.addAttribute("customer", customer);
            return "customer/create";
        }
        List<Customer> email = customerService.findEmailByEmail(customer.getEmail());
        if(email.size() != 0){
            model.addAttribute("err", "Email has been exist");
            model.addAttribute("customer", customer);
            return "customer/create";
        }
        customer.setBalance(BigDecimal.ZERO);
        customerService.save(customer);
        model.addAttribute("message", "Added new customer successfully!!!");
        model.addAttribute("customer", new Customer());
        return "customer/create";
    }
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable long id, Model model){
        Optional<Customer> customerOptional = customerService.findById(id);
        if(!customerOptional.isPresent()){
            return "404";
        }
        model.addAttribute("customer", customerOptional.get());
        return "customer/update";
    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable long id,@Validated @ModelAttribute Customer customer, BindingResult bindingResult, Model model){
        if(bindingResult.hasFieldErrors()){
            model.addAttribute("errors", true);
            model.addAttribute("customer", customer);
            return "customer/update";
        }
        List<Customer> email = customerService.findEmailByEmail(customer.getEmail());
        if(email.size() != 0){
            model.addAttribute("err", "Email has been exist");
            model.addAttribute("customer", customer);
            return "customer/create";
        }
        Optional<Customer> customerOptional = customerService.findById(id);
        customer.setBalance(customerOptional.get().getBalance());
        customer.setId(id);
        customerService.save(customer);
        model.addAttribute("message", "Updated customer's information");
        model.addAttribute("customer", customer);
        return "customer/update";
    }
    @GetMapping("/history")
    public String showHistoryPage( Model model){
        List<Transfer> transfers =  transferService.findAll();
        model.addAttribute("transfers", transfers);

        List<Deposit> deposits = depositService.findAll();
        model.addAttribute("deposits", deposits);

        List<Withdraw> withdraws = withdrawService.findAll();
        model.addAttribute("withdraws", withdraws);
        return "customer/history";
    }
}
