package com.example.banking.service.customer;

import com.example.banking.model.Customer;
import com.example.banking.model.Deposit;
import com.example.banking.model.Transfer;
import com.example.banking.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomerService extends IGeneralService<Customer> {
    List<Customer> findAllByFullNameLikeOrEmailLikeOrPhoneLikeOrAddressLike(String fullName, String email, String phone, String address);
    List<Customer> findAllByIdNot(long id);
    Deposit deposit(Deposit deposit);
    Transfer transfer(Transfer transfer);
    List<Customer> findEmailByEmail(String email);
}
