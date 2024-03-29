package com.example.springajaxbanking.service;

import com.example.springajaxbanking.model.Customer;

import java.util.List;
import java.util.Optional;

public interface IGeneralService<T> {
    List<T> findAll();
    Optional<T> findById(Long id);

    Boolean existById(Long id);

    T save(T t);

    void delete(T t);

    Customer deleteById(Long id);
}
