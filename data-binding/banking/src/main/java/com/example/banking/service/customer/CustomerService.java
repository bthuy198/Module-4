package com.example.banking.service.customer;

import com.example.banking.model.Customer;
import com.example.banking.model.Deposit;
import com.example.banking.model.Transfer;
import com.example.banking.repository.CustomerRepository;
import com.example.banking.repository.DepositRepository;
import com.example.banking.repository.TransferRepository;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Customer> findAll() {
//        return customerRepository.findAll();
        return customerRepository.findAllByDeletedFalse();
    }

//    public Iterable<Customer> findAll(boolean isDeleted) {
//        Session session = entityManager.unwrap(Session.class);
//        Filter filter = session.enableFilter("deletedProductFilter");
//        filter.setParameter("isDeleted", isDeleted);
//        Iterable<Customer> customers = customerRepository.findAll();
//        session.disableFilter("deletedProductFilter");
//        return customers;
//    }

    @Override
    public Optional<Customer> findById(Long id) {
//        return customerRepository.findById(id);
        return customerRepository.findByIdAndDeletedFalse(id);
    }

    @Override
    public Boolean existById(Long id) {
        return customerRepository.existsById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Customer customer) {
        customer.setDeleted(true);
        customerRepository.delete(customer);
    }

    @Override
    public Customer deleteById(Long id) {
        customerRepository.deleteById(id);
        return null;
    }

    @Override
    public List<Customer> findAllByFullNameLikeOrEmailLikeOrPhoneLikeOrAddressLike(String fullName, String email, String phone, String address) {
//        return customerRepository.findAllByFullNameLikeOrEmailLikeOrPhoneLikeOrAddressLike(fullName, email, phone, address);
        return customerRepository.findAllByFullNameLikeOrEmailLikeOrPhoneLikeOrAddressLikeAndDeletedFalse(fullName, email, phone, address);
    }

    @Override
    public List<Customer> findAllByIdNot(long id) {
//        return customerRepository.findAllByIdNot(id);
        return customerRepository.findAllByIdNotAndDeletedFalse(id);
    }

    @Override
    public Deposit deposit(Deposit deposit) {
        depositRepository.save(deposit);
        customerRepository.save(deposit.getCustomer());
        return deposit;
    }

    @Override
    public Transfer transfer(Transfer transfer) {
        transferRepository.save(transfer);
        customerRepository.save(transfer.getRecipient());
        customerRepository.save(transfer.getSender());
        return transfer;
    }

    @Override
    public List<Customer> findEmailByEmail(String email) {
        return customerRepository.findEmailByEmail(email);
    }
}
