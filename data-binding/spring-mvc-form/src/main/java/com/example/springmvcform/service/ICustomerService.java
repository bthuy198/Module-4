package com.example.springmvcform.service;

import com.example.springmvcform.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    void save(Customer customer);

    Customer findById(long id);

    void update(long id, Customer customer);

    void remove(long id);
}
