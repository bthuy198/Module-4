package com.example.banking.repository;

import com.example.banking.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByFullNameLikeOrEmailLikeOrPhoneLikeOrAddressLike(String fullName, String email, String phone, String address);
    List<Customer> findAllByIdNot(long id);
    @Query(value = "from Customer where email= ?1")
    List<Customer> findEmailByEmail(@Param("email") String email);
}
