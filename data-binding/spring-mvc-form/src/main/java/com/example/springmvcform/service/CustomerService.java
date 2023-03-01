package com.example.springmvcform.service;

import com.example.springmvcform.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerService implements ICustomerService{
    private static final Map<Long, Customer> listCustomer;
    static {
        listCustomer = new HashMap<>();
        listCustomer.put(1L, new Customer(1L, "Thủy", "123@gmail.com", "Huế"));
        listCustomer.put(2L, new Customer(2L, "Thủy Đặng", "thuy@gmail.com", "HN"));
        listCustomer.put(3L, new Customer(3L, "Bích Thủy", "thuydang@gmail.com", "ĐN"));
        listCustomer.put(4L, new Customer(4L, "Thủy123", "thuy123@gmail.com", "HCM"));
    }
    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(listCustomer.values());
    }

    @Override
    public void save(Customer customer) {
        listCustomer.put(customer.getId(), customer);
    }

    @Override
    public Customer findById(long id) {
        return listCustomer.get(id);
    }

    @Override
    public void update(long id, Customer customer) {
        listCustomer.put(id, customer);
    }

    @Override
    public void remove(long id) {
        listCustomer.remove(id);
    }
}
