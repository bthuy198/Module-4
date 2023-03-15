package com.example.springajaxbanking.api;

import com.example.springajaxbanking.exception.DataInputException;
import com.example.springajaxbanking.exception.FieldExistsException;
import com.example.springajaxbanking.exception.ResourceNotFoundException;
import com.example.springajaxbanking.model.*;
import com.example.springajaxbanking.model.dto.CustomerDTO;
import com.example.springajaxbanking.service.customer.ICustomerService;
import com.example.springajaxbanking.utils.AppUtils;
import com.mysql.cj.xdevapi.JsonNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/customers")
public class CustomerAPI {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {

        List<CustomerDTO> customerDTOS = customerService.findAllCustomerDTO();

        return new ResponseEntity<>(customerDTOS, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO, BindingResult bindingResult) {

//        if(bindingResult.hasFieldErrors()){
//            return appUtils.mapErrorToResponse(bindingResult);
//        }

        Boolean existEmail = customerService.existsByEmailEquals(customerDTO.getEmail());

        if (existEmail) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        customerDTO.setId(null);
        customerDTO.setBalance(BigDecimal.ZERO);
        customerDTO.getLocationRegion().setId(null);

        Customer customer = customerDTO.toCustomer();
        customerService.save(customer);
        customerDTO = customer.toCustomerDTO();

        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable long id) {
        Optional<Customer> optionalCustomer = customerService.findById(id);
        if (optionalCustomer.isPresent()) {
            return new ResponseEntity<>(optionalCustomer.get().toCustomerDTO(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer = customerService.findById(id);

        if (!optionalCustomer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        customerDTO.setId(id);
        customerDTO.setBalance(optionalCustomer.get().getBalance());
        customerDTO.setLocationRegion(optionalCustomer.get().getLocationRegion().toLocationRegionDTO());

//        LocationRegion locationRegion;
//        if (customerDTO.getLocationRegion() == null) {
//            locationRegion = optionalCustomer.get().getLocationRegion();
//        } else {
//            locationRegion = customerDTO.getLocationRegion().toLocationRegion();
//        }

//        customerDTO.setLocationRegion(locationRegion.toLocationRegionDTO());
        customerService.save(customerDTO.toCustomer());

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> remove(@PathVariable long id){

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/deposit/{id}")
    public ResponseEntity<?> deposit(@PathVariable long id, @Validated @RequestBody Deposit deposit, BindingResult bindingResult) {
        Optional<Customer> customerOptional = customerService.findById(id);

        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Customer customer = customerOptional.get();

        BigDecimal transactionAmount = deposit.getTransactionAmount();
        BigDecimal currentBalance = customerOptional.get().getBalance();
        BigDecimal newBalance = currentBalance.add(transactionAmount);

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        customer.setBalance(newBalance);
        customerService.save(customer);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/withdraw/{id}")
    public ResponseEntity<?> withdraw(@PathVariable long id, @Validated @RequestBody Withdraw withdraw, BindingResult bindingResult) {
        Optional<Customer> customerOptional = customerService.findById(id);

        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Customer customer = customerOptional.get();

        BigDecimal transactionAmount = withdraw.getTransactionAmount();
        BigDecimal currentBalance = customerOptional.get().getBalance();

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        if (transactionAmount.compareTo(currentBalance) == 1) {
            throw new DataInputException("Your balance not enough to withdraw");
        }
        BigDecimal newBalance = currentBalance.add(transactionAmount);


        customer.setBalance(newBalance);
        customerService.save(customer);


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/transfer/{id}")
    public ResponseEntity<?> transfer(@PathVariable long id, @Validated @RequestBody Transfer transfer, BindingResult bindingResult) {
        Optional<Customer> optionalSender = customerService.findById(id);
        long recipientId = transfer.getRecipient().getId();
        Optional<Customer> optionalRecipient = customerService.findById(recipientId);

        Customer sender = optionalSender.get();
        Customer recipient = optionalRecipient.get();

        if(bindingResult.hasFieldErrors()){
            return appUtils.mapErrorToResponse(bindingResult);
        }

        if(!(optionalSender.isPresent() || optionalRecipient.isPresent())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        BigDecimal senderBalance = sender.getBalance();
        BigDecimal recipientBalance = recipient.getBalance();

        BigDecimal transferAmount = transfer.getTransferAmount();
        Long fees = 10L;


        BigDecimal feesAmount = transferAmount.multiply(BigDecimal.valueOf(fees)).divide(BigDecimal.valueOf(100L));
        BigDecimal totalAmount = transferAmount.add(feesAmount);


            if(sender.getId() == recipient.getId()){
                throw new FieldExistsException("Invalid Recipient");
            }
            if(senderBalance.compareTo(totalAmount) < 0){
                throw new DataInputException("Your balance not enough to make this transaction");
            }

        BigDecimal sender_newBalance = senderBalance.subtract(totalAmount);
        sender.setBalance(sender_newBalance);

        BigDecimal recipient_newBalance = recipientBalance.add(transferAmount);
        recipient.setBalance(recipient_newBalance);

        transfer.setRecipient(recipient);
        transfer.setSender(sender);
        transfer.setTransferAmount(transferAmount);
        transfer.setTotalAmount(totalAmount);
        transfer.setFees(fees);
        transfer.setFeesAmount(feesAmount);

        customerService.transfer(transfer);

        transfer.setTransferAmount(BigDecimal.ZERO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
