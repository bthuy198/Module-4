package com.example.springajaxbanking.repository;

import com.example.springajaxbanking.model.Customer;
import com.example.springajaxbanking.model.dto.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
//    List<Customer> findAllByFullNameLikeOrEmailLikeOrPhoneLike(String fullName, String email, String phone);
    List<Customer> findAllByIdNot(long id);
    @Query(value = "from Customer where email= ?1")
    List<Customer> findEmailByEmail(@Param("email") String email);

//    List<Customer> findAllByFullNameLikeOrEmailLikeOrPhoneLikeOrAddressLikeAndDeletedFalse(String fullName, String email, String phone, String address);

    List<Customer> findAllByIdNotAndDeletedFalse(Long id);

    List<Customer> findAllByDeletedFalse();

    Optional<Customer> findByIdAndDeletedFalse(Long id);
    Boolean existsByEmailEquals(String email);

    @Query("SELECT NEW com.example.springajaxbanking.model.dto.CustomerDTO (" +
            "cus.id, " +
            "cus.fullName, " +
            "cus.email, " +
            "cus.phone, " +
            "cus.balance, " +
            "cus.locationRegion" +
            ") " +
            "FROM Customer AS cus"
    )
    List<CustomerDTO> findAllCustomerDTO();

}
