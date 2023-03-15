package com.example.springajaxbanking.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "transfers")
public class Transfer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name= "sender_id", referencedColumnName = "id", nullable = false)
    private Customer sender;
    @ManyToOne
    @JoinColumn(name= "recipient_id", referencedColumnName = "id", nullable = false)
    private Customer recipient;
    @Column(name="transfer_amount", precision = 10, scale = 0, nullable = false)
    @NotNull(message = "Enter the amount you want to transfer")
    @DecimalMin(value = "10000", message = "Minimum transfer amount is 10,000 VNĐ")
    @DecimalMax(value = "1000000000", message = "Maximum transfer amount is 1.000.000.000 VNĐ")
    private BigDecimal transferAmount;
    private long fees;
    @Column(name="fees_amount", precision = 10, scale = 0, nullable = false)
    private BigDecimal feesAmount;
    @Column(name="total_amount", precision = 10, scale = 0, nullable = false)
    private BigDecimal totalAmount;

    public Transfer() {
    }

    public Transfer(long id, Customer sender, Customer recipient, BigDecimal transferAmount, long fees, BigDecimal feesAmount, BigDecimal totalAmount) {
        this.id = id;
        this.sender = sender;
        this.recipient = recipient;
        this.transferAmount = transferAmount;
        this.fees = fees;
        this.feesAmount = feesAmount;
        this.totalAmount = totalAmount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getSender() {
        return sender;
    }

    public void setSender(Customer sender) {
        this.sender = sender;
    }

    public Customer getRecipient() {
        return recipient;
    }

    public void setRecipient(Customer recipient) {
        this.recipient = recipient;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transactionAmount) {
        this.transferAmount = transactionAmount;
    }

    public long getFees() {
        return fees;
    }

    public void setFees(long fees) {
        this.fees = fees;
    }

    public BigDecimal getFeesAmount() {
        return feesAmount;
    }

    public void setFeesAmount(BigDecimal feesAmount) {
        this.feesAmount = feesAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
