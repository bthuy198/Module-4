package com.example.banking.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "withdraws")
public class Withdraw extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name= "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;
    @Column(name="transaction_amount", precision = 10, scale = 0, nullable = false)
    @NotNull(message = "Enter the amount you want to withdraw")
    @DecimalMin(value = "10000", message = "Minimum withdraw amount is 10,000 VNĐ")
    @DecimalMax(value = "1000000000", message = "Maximum withdraw amount is 1.000.000.000 VNĐ")
    private BigDecimal transactionAmount;

    public Withdraw() {
    }

    public Withdraw(long id, Customer customer, BigDecimal transactionAmount) {
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
