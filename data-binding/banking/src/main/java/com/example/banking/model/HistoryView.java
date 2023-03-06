package com.example.banking.model;

import groovy.transform.Immutable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "view_change_balance")
@Immutable
public class HistoryView {
    @Id
    private long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name="transaction_amount")
    private BigDecimal transactionAmount;
    @Column(name="method")
    private String method;

    public HistoryView() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createAt) {
        this.createdAt = createAt;
    }
}
