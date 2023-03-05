package com.example.banking.model;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "deposits")
public class Deposit extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name= "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;
    @Column(name="transaction_amount", precision = 10, scale = 0, nullable = false)
    @NotNull(message = "Enter the amount you want to deposit")
    @DecimalMin(value = "10000", message = "Minimum deposit amount is 10,000 VNĐ")
    @DecimalMax(value = "1000000000", message = "Maximum deposit amount is 1.000.000.000 VNĐ")
    private BigDecimal transactionAmount;

    public Deposit() {
    }

    public Deposit(long id, Customer customer, BigDecimal transactionAmount) {
        this.id = id;
        this.customer = customer;
        this.transactionAmount = transactionAmount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
