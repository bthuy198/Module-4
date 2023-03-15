package com.example.springajaxbanking.service.customer;

import com.example.springajaxbanking.model.Customer;
import com.example.springajaxbanking.model.Deposit;
import com.example.springajaxbanking.model.Transfer;
import com.example.springajaxbanking.model.dto.CustomerDTO;
import com.example.springajaxbanking.service.IGeneralService;

import java.util.List;

public interface ICustomerService extends IGeneralService<Customer> {
//    List<Customer> findAllByFullNameLikeOrEmailLikeOrPhoneLikeOrAddressLike(String fullName, String email, String phone, String address);
    List<Customer> findAllByIdNot(long id);
    Deposit deposit(Deposit deposit);
    Transfer transfer(Transfer transfer);
    List<Customer> findEmailByEmail(String email);
    Boolean existsByEmailEquals(String email);

    List<CustomerDTO> findAllCustomerDTO();
}
