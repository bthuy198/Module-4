package com.example.springajaxbanking.service.customer;

import com.example.springajaxbanking.model.Customer;
import com.example.springajaxbanking.model.Deposit;
import com.example.springajaxbanking.model.Transfer;
import com.example.springajaxbanking.model.Withdraw;
import com.example.springajaxbanking.model.dto.CustomerDTO;
import com.example.springajaxbanking.service.IGeneralService;

import java.math.BigDecimal;
import java.util.List;

public interface ICustomerService extends IGeneralService<Customer> {
//    List<Customer> findAllByFullNameLikeOrEmailLikeOrPhoneLikeOrAddressLike(String fullName, String email, String phone, String address);
    List<Customer> findAllByIdNot(long id);
    Deposit deposit(Deposit deposit);
    Withdraw withdraw(Withdraw withdraw);
    Transfer transfer(Transfer transfer);
    List<Customer> findEmailByEmail(String email);
    Boolean existsByEmailEquals(String email);

    List<CustomerDTO> findAllCustomerDTO();
    List<CustomerDTO> findAllByDeletedIsFalse();

    void incrementBalance(BigDecimal transactionAmount, Customer customer);

    void decrementBalance(BigDecimal transactionAmount, Customer customer);
}
